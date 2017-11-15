package com.helpscout.mergeProfiles.repository;

import com.helpscout.mergeProfiles.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepositoryCustom {
  @Procedure(name = "duplicates")
  List<Object[]> duplicates(@Param("fname") String inParam1, @Param("lname") String inParams);
  
 
}
