package com.soulsarch.PasswordManager.service;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.model.Role;
import com.soulsarch.PasswordManager.model.Status;
import com.soulsarch.PasswordManager.repository.URLInfoRepository;
import com.soulsarch.PasswordManager.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private URLInfoRepository urlInfoRepository;

    @InjectMocks
    private URLInfoService urlInfoService;
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnGetAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(getAllUsers());
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        Assertions.assertNotNull(allUsers);
        Assertions.assertEquals(3, allUsers.size());
    }

/*    @Test
    public void shouldReturnGetListByUser() {
        Mockito.when(userRepository.findAll()).thenT
    }*/

    private List<User> getAllUsers() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setId(1);
        user1.setPassword("pass");
        user1.setFullname("userFirst");
        user1.setUsername("user@mail.ru");
        user1.setRole(Role.USER);
        user1.setStatus(Status.ACTIVE);
        user1.setPhone("89503332211");
        user1.setUrlInformationList(getAllUrl());

        user2.setId(2);
        user2.setPassword("pass2");
        user2.setFullname("userSecond");
        user2.setUsername("user2@mail.ru");
        user2.setRole(Role.USER);
        user2.setStatus(Status.BANNED);
        user2.setPhone("89503332212");
        user2.setUrlInformationList(null);

        user3.setId(3);
        user3.setPassword("pass3");
        user3.setFullname("userThird");
        user3.setUsername("user3@mail.ru");
        user3.setRole(Role.MODERATOR);
        user3.setStatus(Status.ACTIVE);
        user3.setPhone("89503332213");
        user3.setUrlInformationList(getAllUrl());

        return List.of(user1, user2, user3);
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
