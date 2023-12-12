package com.online_shop.MAP_Labor_2_Spring.repositories;

import com.online_shop.MAP_Labor_2_Spring.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}


//public interface UserRepository extends CrudRepository<User, Integer> {
//    User findByEmail(String email);
//    User updateByEmail(String email, User user);
//    boolean existsByEmail(String email);
//    void deleteByEmail(String email);
//}
