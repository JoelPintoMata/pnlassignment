package com.example.pnl.pakketten.dao;

import com.example.pnl.BaseTest;
import com.example.pnl.pakketten.model.Pakketten;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.pnl.apa.types.ProductType.PRODUCT_AGT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Sql({"classpath:schema.sql"})
class PakketenDAOTest extends BaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void whenMockJdbcTemplate_thenReturnCorrectEmployeeCount() {
        String postCode = "1234AB";
        String huisnr = "1";
        String huisnrtvg = "2";
        String producttype = PRODUCT_AGT.getValue();

        PakkettenDAO pakkettenDAO = new PakkettenDAO(jdbcTemplate);
        jdbcTemplate.execute("insert into pakketten(totaal, bbpx, agt, totaal_postbusbezorgd_dt, bbpx_postbusbezorgd_dt, agt_postbusbezorgd_dt, ingangsdt, eta_van, postcd, huisnr, huisnrtvg, producttype) " +
                "values(" + pakkettenList.get(0).getTotaal() + "," +  pakkettenList.get(0).getBbpx() + "," + pakkettenList.get(0).getAgt() + ", '" + pakkettenList.get(0).getTotaalPostbusbezorgdDt() + "', '" + pakkettenList.get(0).getBbpxPostbusbezorgdDt() + "', '" + pakkettenList.get(0).getAgtPostbusbezorgdDt() + "', 30, '" + LocalDateTime.now().toString() + "', '" + postCode + "', '" + huisnr + "', '" + huisnrtvg + "', '" + producttype + "')");

        List<Pakketten> pakkettenListFromDB = pakkettenDAO.getPakketten(postCode, huisnr, huisnrtvg, producttype, LocalDate.now().toString());
        assertEquals(pakkettenList.get(0).getTotaal(), pakkettenListFromDB.get(0).getTotaal());
        assertEquals(pakkettenList.get(0).getTotaalPostbusbezorgdDt(), pakkettenListFromDB.get(0).getTotaalPostbusbezorgdDt());
        assertEquals(pakkettenList.get(0).getBbpx(), pakkettenListFromDB.get(0).getBbpx());
        assertEquals(pakkettenList.get(0).getBbpxPostbusbezorgdDt(), pakkettenListFromDB.get(0).getBbpxPostbusbezorgdDt());
        assertEquals(pakkettenList.get(0).getAgt(), pakkettenListFromDB.get(0).getAgt());
        assertEquals(pakkettenList.get(0).getAgtPostbusbezorgdDt(), pakkettenListFromDB.get(0).getAgtPostbusbezorgdDt());
    }
}