package com.example.pnl.util;

import com.example.pnl.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SanitizerTest extends BaseTest {

    @Test
    public void givenUnSanitizeString_whenCall_thenSanitize() {
        String unSanitizedString = "{foo:\"bar\"}";
        Assert.assertEquals("{\"foo\":\"bar\"}",
                Sanitizer.sanitize(unSanitizedString));
    }
}
