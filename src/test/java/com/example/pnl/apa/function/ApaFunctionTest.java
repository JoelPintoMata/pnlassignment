package com.example.pnl.apa.function;

import com.example.pnl.ApaFunctionApplication;
import com.example.pnl.BaseTest;
import com.example.pnl.apa.function.response.ApaResponse;
import com.example.pnl.apa.service.ApaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApaFunctionApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApaFunctionTest extends BaseTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApaFunction apaFunction;

    @Test
    public void givenId_whenCallFunction_thenReturnResponse() throws URISyntaxException, JsonProcessingException {
        ApaService apaService = Mockito.mock(ApaService.class);
        Mockito.when(apaService.getApaData(Mockito.any())).thenReturn(apaResponse);

        ReflectionTestUtils.setField(apaFunction, "apaService", apaService);
        ResponseEntity<String> result = this.restTemplate.exchange(
                RequestEntity.post(new URI("/apaFunction")).body("1"), String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ApaResponse apaResponseFromFunction = objectMapper.readValue(result.getBody(), ApaResponse.class);

        Mockito.verify(apaService, Mockito.times(1)).getApaData(Mockito.any());
        Assert.assertEquals(apaResponse.getTotaal().getAantal(), apaResponseFromFunction.getTotaal().getAantal());
        Assert.assertEquals(apaResponse.getTotaal().getBezorgmoment(), apaResponseFromFunction.getTotaal().getBezorgmoment());
        Assert.assertEquals(apaResponse.getProducts().size(), apaResponseFromFunction.getProducts().size());
        Assert.assertEquals(apaResponse.getProducts().get(0).getAantal(), apaResponseFromFunction.getProducts().get(0).getAantal());
    }
}