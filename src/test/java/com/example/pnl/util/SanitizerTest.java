package com.example.pnl.util;

import com.example.pnl.BaseTest;
import com.example.pnl.adresmeldingcountid.dto.AdresMeldingCountIdDTO;
import com.example.pnl.apa.function.response.ApaResponse;
import com.example.pnl.apa.service.ApaService;
import com.example.pnl.otrapplicationparameter.dao.OtrApplicationParameterDAO;
import com.example.pnl.pakketten.dao.PakkettenDAO;
import com.example.pnl.util.AdresMeldingCountIdAssembler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SanitizerTest extends BaseTest {

    @Test
    public void givenUnsanitizeString_whenCall_thenSanitize() {
        String unsanitizedString = "{foo:\"bar\"}";
        Assert.assertEquals("{\"foo\":\"bar\"}",
                Sanitizer.sanitize(unsanitizedString));
    }
}
