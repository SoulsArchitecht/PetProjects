package com.soulsarch.PasswordManager.service;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.repository.URLInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class URLInfoServiceTest {

    @Mock
    private URLInfoRepository urlInfoRepository;
    @InjectMocks
    private URLInfoService urlInfoService;

    @Test
    public void shouldReturnUrlInfo() {
        List<URLInformation> urlInformationList = getAllUrl();
        Mockito.when(urlInfoRepository.findAll()).thenReturn(urlInformationList);

        List<URLInformation> fromRep = urlInfoService.infoList();
        Assertions.assertNotNull(fromRep);
        Assertions.assertEquals(2, fromRep.size());
        Assertions.assertEquals(urlInformationList.get(1), fromRep.get(1));
    }

    private List<URLInformation> getAllUrl() {
        URLInformation firstUrl = new URLInformation();
        URLInformation secondUrl = new URLInformation();

        firstUrl.setUrl("Test");
        firstUrl.setId(99);
        firstUrl.setEmail("user@mail.ru");
        firstUrl.setUser(null);
        firstUrl.setNickname("NickTest");
        firstUrl.setDescription("TestDescr");
        firstUrl.setPassword("1111");

        secondUrl.setUrl("Test2");
        secondUrl.setId(98);
        secondUrl.setEmail("user2@mail.ru");
        secondUrl.setUser(null);
        secondUrl.setNickname("NickTest2");
        secondUrl.setDescription("TestDescr2");
        secondUrl.setPassword("2222");

        return List.of(firstUrl, secondUrl);
    }
}
