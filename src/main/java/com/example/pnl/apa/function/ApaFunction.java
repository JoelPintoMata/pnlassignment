package com.example.pnl.apa.function;

import com.example.pnl.apa.function.response.ApaResponse;
import com.example.pnl.apa.service.ApaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

/**
 * Spring Cloud Function to retrieve a JSON representation of an {@link ApaResponse}
 */
@Component
public class ApaFunction implements UnaryOperator<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApaFunction.class);

    @Autowired
    private ApaService apaService;

    @Override
    public String apply(String id) {
        ApaResponse apaResponse = this.apaService.getApaData(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(apaResponse);
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not generate reply for id: {}", id, e);
        }
        LOGGER.info("Executing function for id: {}", id);
        return result;
    }
}
