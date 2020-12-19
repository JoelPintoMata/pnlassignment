package com.example.pnl.pakketten.dao;

import com.example.pnl.pakketten.model.Pakketten;
import com.example.pnl.util.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Repository
public class PakkettenDAO {

    private static final Logger logger = LoggerFactory.getLogger(PakkettenDAO.class);

    private static final String SELECT = "select totaal as totaal_pakketten, totaal_postbusbezorgd_dt as up_pakketten, \r\n" +
            " bbpx as totaal_bbpx, bbpx_postbusbezorgd_dt as up_bbpx, \r\n" +
            " agt as totaal_agt, agt_postbusbezorgd_dt as up_agt \r\n" +
            "from pakketten\r\n" +
            "where eta_van::date = ? and\r\n" +
            "ingangsdt >= ? and\r\n" +
            "postcd = ? and\r\n" +
            "huisnr = ? and\r\n" +
            "(huisnrtvg = ? or huisnrtvg is null) and \r\n" +
            "producttype = ?";

    private static final int INTERVAL = 14;

    private final JdbcTemplate jdbcTemplate;

    public PakkettenDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a list of {@link Pakketten }
     *
     * @param postcode the pakket postal code
     * @param huisnr the pakket delivery postal code
     * @param huisnrtvg the pakket delivery house number
     * @param producttype the main product type
     * @param bezorgDatum the pakket delivery date
     * @return a list
     */
    public List<Pakketten> getPakketten(String postcode, String huisnr, String huisnrtvg, String producttype, Object bezorgDatum) {
        List<Pakketten> pakkettenList = new ArrayList<>();

        Optional<Date> bezorgDatumAsSqlDateOptional = DateTimeUtils.toSqlDate(bezorgDatum);
        if (bezorgDatumAsSqlDateOptional.isPresent()) {
            pakkettenList = jdbcTemplate.query(SELECT, preparedStatement -> {
                preparedStatement.setDate(1, bezorgDatumAsSqlDateOptional.get());
                preparedStatement.setString(2, minusDaysFromNow());
                preparedStatement.setString(3, postcode);
                preparedStatement.setString(4, huisnr);
                preparedStatement.setString(5, huisnrtvg);
                preparedStatement.setString(6, producttype);
            }, new PakketenRowMapper());
            logger.error("Retrieved Pakketen for postcode: {}, huisnr: {}, huisnrtvg: {}, bezorgDatum: {}",
                    postcode, huisnr, huisnrtvg, bezorgDatum);
        } else {
            logger.error("Could not parse bezorgDatum: {}", bezorgDatum);
        }
        return pakkettenList;
    }

    private String minusDaysFromNow() {
        GregorianCalendar aCalendar = new GregorianCalendar();
        aCalendar.add(GregorianCalendar.DATE, -INTERVAL);

        return new Date(aCalendar.getTimeInMillis()).toString();
    }
}
