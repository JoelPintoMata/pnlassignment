package com.example.pnl.apa.service;

import com.example.pnl.adresmeldingcountid.dto.AdresMeldingCountIdDTO;
import com.example.pnl.apa.function.response.ApaResponse;
import com.example.pnl.apa.function.response.Product;
import com.example.pnl.otrapplicationparameter.dao.OtrApplicationParameterDAO;
import com.example.pnl.pakketten.dao.PakkettenDAO;
import com.example.pnl.pakketten.model.Pakketten;
import com.example.pnl.util.AdresMeldingCountIdAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.pnl.apa.types.ProductType.*;

@Service
public class ApaService {

    private static final Logger logger = LoggerFactory.getLogger(ApaService.class);

    private final PakkettenDAO pakkettenDAO;

    private String agt = PRODUCT_AGT.getValue();

    public ApaService(OtrApplicationParameterDAO otrApplicationParameterDAO,
                      PakkettenDAO pakkettenDAO) {
        this.pakkettenDAO = pakkettenDAO;

        // Retrieve this service instance configuration properties
        otrApplicationParameterDAO.getProductType().ifPresent(x -> agt = x);
    }

    /**
     * Builds an {@link ApaResponse} instance
     * @param id an id
     * @return a response
     */
    public ApaResponse getApaData(String id) {
        ApaResponse apaResponse = new ApaResponse();

        List<Pakketten> pakkettenList = getPakketen(id);

        buildResponse(apaResponse, pakkettenList);

        logger.debug("Response built for id: {}", id);
        return apaResponse;
    }

    private void buildResponse(ApaResponse apaResponse, List<Pakketten> pakkettenList) {
        if (!pakkettenList.isEmpty()) {
            addProductToApaResponse(apaResponse, pakkettenList.get(0).getTotaal(), pakkettenList.get(0).getTotaalPostbusbezorgdDt(), PRODUCT_PKT.getValue());
            addProductToApaResponse(apaResponse, pakkettenList.get(0).getBbpx(), pakkettenList.get(0).getBbpxPostbusbezorgdDt(), PRODUCT_BBPX.getValue());
            addProductToApaResponse(apaResponse, pakkettenList.get(0).getAgt(), pakkettenList.get(0).getAgtPostbusbezorgdDt(), PRODUCT_AGT.getValue());
        }
    }

    private List<Pakketten> getPakketen(String id) {
        AdresMeldingCountIdDTO adresMeldingCountIdDTO = AdresMeldingCountIdAssembler.fromEncryptedString(id);
        if (adresMeldingCountIdDTO == null) {
            return new ArrayList<>();
        }
        return pakkettenDAO.getPakketten(adresMeldingCountIdDTO.getPostcode(),
                adresMeldingCountIdDTO.getHuisnr(),
                adresMeldingCountIdDTO.getHuisnrtvg(),
                agt,
                adresMeldingCountIdDTO.getBezorgDatum());
    }

    /**
     * Adds a new product to a {@link ApaResponse}
     * @param apaResponse an ApaResponse
     * @param totaal the total value for the given product type
     * @param timestamp a timestamp
     * @param productType the product type
     */
    private void addProductToApaResponse(ApaResponse apaResponse, int totaal, Timestamp timestamp, String productType) {
        if (totaal > 0) {
            Product product = new Product();
            product.setType(productType);
            product.setAantal(totaal);

            apaResponse.addProduct(product);
            apaResponse.getTotaal().setAantal(totaal + apaResponse.getTotaal().getAantal());
            if (timestamp != null) {
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                product.setBezorgmoment(localDateTime);
                if (apaResponse.getTotaal().getBezorgmoment() == null
                        || apaResponse.getTotaal().getBezorgmoment().compareTo(localDateTime) > 0) {
                    apaResponse.getTotaal().setBezorgmoment(localDateTime);
                }
            }
        }
    }
}
