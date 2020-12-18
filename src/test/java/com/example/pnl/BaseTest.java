package com.example.pnl;

import com.example.pnl.apa.function.response.ApaResponse;
import com.example.pnl.apa.function.response.Product;
import com.example.pnl.apa.function.response.Totaal;
import com.example.pnl.pakketten.model.Pakketten;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.pnl.apa.types.ProductType.PRODUCT_AGT;

public class BaseTest {

    public final List<Pakketten> pakkettenList;
    public final ApaResponse apaResponse;

    public BaseTest() {
        Pakketten pakketten = new Pakketten();
        pakketten.setTotaal(1);
        pakketten.setTotaalPostbusbezorgdDt(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        pakketten.setBbpx(1);
        pakketten.setBbpxPostbusbezorgdDt(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
        pakketten.setAgt(1);
        pakketten.setAgtPostbusbezorgdDt(Timestamp.valueOf(LocalDateTime.now().minusDays(3)));
        pakketten.setProductType(PRODUCT_AGT.getValue());
        pakkettenList = new ArrayList<>();
        pakkettenList.add(pakketten);

        Totaal totaal = new Totaal();
        totaal.setAantal(1);
        totaal.setBezorgmoment(LocalDateTime.now().minusDays(2));
        Product product = new Product();
        product.setAantal(1);
        product.setBezorgmoment(LocalDateTime.now().minusDays(1));
        apaResponse = new ApaResponse();
        apaResponse.setTotaal(totaal);
        apaResponse.addProduct(product);
    }
}
