package com.helpscout.mergeProfiles.repository;

import org.springframework.data.repository.CrudRepository;
import com.helpscout.mergeProfiles.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

}
