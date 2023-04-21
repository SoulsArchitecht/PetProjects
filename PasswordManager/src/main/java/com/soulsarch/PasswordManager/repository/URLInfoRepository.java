package com.soulsarch.PasswordManager.repository;

import com.soulsarch.PasswordManager.entity.URLInformation;
import com.soulsarch.PasswordManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface URLInfoRepository extends JpaRepository<URLInformation, Integer> {

   String query1 = "SELECT * FROM urlinformation  WHERE "
            + "url LIKE %:keyword%"
            + " OR email LIKE %:keyword%"
            + " OR password LIKE %:keyword%"
            + " OR description LIKE %:keyword%";

   String query2 = "SELECT * FROM urlinformation WHERE "
           + "user_id =:userId";

    String query33 = "SELECT * FROM urlinformation WHERE url LIKE %:keyword%";
    String query34 = "SELECT * FROM urlinformation WHERE url LIKE %?1%";
    @Query(value = query1, nativeQuery = true)
    List<URLInformation> search(@Param("keyword") String keyword);

    @Query(value = query2, nativeQuery = true)
    List<URLInformation> findAllByUser(@Param("userId") String userId);


}
