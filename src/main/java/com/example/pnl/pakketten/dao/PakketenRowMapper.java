package com.example.pnl.pakketten.dao;

import com.example.pnl.pakketten.model.Pakketten;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PakketenRowMapper implements RowMapper<Pakketten> {

    public Pakketten mapRow(ResultSet resultSet, int i) throws SQLException {
        Pakketten pakketten = new Pakketten();
        pakketten.setTotaal(resultSet.getInt("totaal"));
        pakketten.setTotaalPostbusbezorgdDt(resultSet.getTimestamp("totaal_postbusbezorgd_dt"));
        pakketten.setBbpx(resultSet.getInt("bbpx"));
        pakketten.setBbpxPostbusbezorgdDt(resultSet.getTimestamp("bbpx_postbusbezorgd_dt"));
        pakketten.setAgt(resultSet.getInt("agt"));
        pakketten.setAgtPostbusbezorgdDt(resultSet.getTimestamp("agt_postbusbezorgd_dt"));
        return pakketten;
    }
}