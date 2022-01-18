package com.soulsarch.PasswordManager.repository;

import com.soulsarch.PasswordManager.entity.URLInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface URLInfoRepository extends JpaRepository<URLInformation, Integer> {
    //String keyword = null;
   String query1 = "SELECT * FROM urlinformation  WHERE "
            + "url LIKE %:keyword%"
            + " OR email LIKE %:keyword%"
            + " OR password LIKE %:keyword%";

    String query33 = "SELECT * FROM urlinformation WHERE url LIKE %:keyword%";
    String query34 = "SELECT * FROM urlinformation WHERE url LIKE %?1%";
    @Query(value = query1, nativeQuery = true)
    public List<URLInformation> search(@Param("keyword") String keyword);


}
