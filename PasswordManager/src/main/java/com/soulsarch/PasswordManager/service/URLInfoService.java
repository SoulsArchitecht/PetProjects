package com.soulsarch.PasswordManager.service;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.repository.URLInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class URLInfoService {

    private final URLInfoRepository repository;

    @Autowired
    public URLInfoService(URLInfoRepository repository) {
        this.repository = repository;
    }

    public List<URLInformation> infoList() {
        return repository.findAll();
    }

    public void save(URLInformation urlInformation) {
        repository.save(urlInformation);
    }

    public URLInformation get(int id) {
        return repository.findById(id).get();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<URLInformation> search (String keyword) {
        return  repository.search(keyword);
    }
}
