package com.example.pnl.apa.service;

import com.example.pnl.BaseTest;
import com.example.pnl.adresmeldingcountid.dto.AdresMeldingCountIdDTO;
import com.example.pnl.apa.function.response.ApaResponse;
import com.example.pnl.otrapplicationparameter.dao.OtrApplicationParameterDAO;
import com.example.pnl.pakketten.dao.PakkettenDAO;
import com.example.pnl.util.AdresMeldingCountIdAssembler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AdresMeldingCountIdAssembler.class)
public class ApaServiceTest extends BaseTest {

    @InjectMocks
    private ApaService apaService;

    @Mock
    private PakkettenDAO pakkettenDAO;

    @Mock
    private OtrApplicationParameterDAO otrApplicationParameterDAO;

    @Before
    public void setup() {
        PowerMockito.mockStatic(AdresMeldingCountIdAssembler.class);
    }

    @Test
    public void givenId_whenCallService_thenServiceInitializedAndDBAccess() {
        String id = "some id";
        AdresMeldingCountIdDTO adresMeldingCountIdDTO = new AdresMeldingCountIdDTO();
        adresMeldingCountIdDTO.setBezorgDatum("1");
        adresMeldingCountIdDTO.setHuisnr("2");
        adresMeldingCountIdDTO.setHuisnrtvg("3");
        adresMeldingCountIdDTO.setPostCode("4");
        Mockito.when(AdresMeldingCountIdAssembler.fromEncryptedString(id)).thenReturn(adresMeldingCountIdDTO);

        Mockito.when(pakkettenDAO.getPakketten(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(pakkettenList);

        Mockito.verify(otrApplicationParameterDAO, Mockito.times(1)).getProductType();
        ApaResponse apaResponse = apaService.getApaData(id);
        Mockito.verify(pakkettenDAO, Mockito.times(1)).getPakketten(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Assert.assertEquals(pakkettenList.get(0).getTotaal() + pakkettenList.get(0).getBbpx() + pakkettenList.get(0).getAgt(), apaResponse.getTotaal().getAantal());
        Assert.assertEquals(pakkettenList.get(0).getTotaal() + pakkettenList.get(0).getBbpx() + pakkettenList.get(0).getAgt(), apaResponse.getProducts().size());
    }
}