package com.soulsarch.PasswordManager.service;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.repository.URLInfoRepository;
import com.soulsarch.PasswordManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final URLInfoRepository urlInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, URLInfoRepository urlInfoRepository) {
        this.userRepository = userRepository;
        this.urlInfoRepository = urlInfoRepository;
    }

    public List<URLInformation> getListByUser(User user) {
       //return userRepository.getUrlInformationList();
       return user.getUrlInformationList();
    }
}
