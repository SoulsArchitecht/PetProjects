package com.soulsarch.PasswordManager.repository;

import com.soulsarch.PasswordManager.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String> {

}
