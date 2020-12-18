package com.example.pnl.otrapplicationparameter.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class OtrApplicationParameterDAO {

    private static final Logger logger = LoggerFactory.getLogger(OtrApplicationParameterDAO.class);

    private static final String SELECT = "select parameter, value from otr_application_parameter where parameter='producttype.agt' OR parameter='producttype.bbpx'";

    private final JdbcTemplate jdbcTemplate;

    public OtrApplicationParameterDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<String> getProductType()  {
        Optional<String> agt = Optional.empty();

        List<Map<String, Object>> otrApplicationParameter = jdbcTemplate.queryForList(SELECT);

        if (isProductTypeAvailable(otrApplicationParameter)) {
            agt = Optional.of((String)otrApplicationParameter.get(0).get("value"));
        }
        return agt;
    }

    private boolean isProductTypeAvailable(List<Map<String, Object>> otrApplicationParameter) {
        boolean flag = false;
        if (otrApplicationParameter.size() == 0) {
            logger.warn("No properties found");
        } else if (otrApplicationParameter.size() > 1) {
            logger.warn("Expected single row");
        } else if (otrApplicationParameter.get(0).get("parameter").equals("producttype.agt")
                && !(otrApplicationParameter.get(0).get("value") instanceof String)) {
            logger.warn("Invalid property type");
        } else {
            flag = true;
        }
        return flag;
    }
}
