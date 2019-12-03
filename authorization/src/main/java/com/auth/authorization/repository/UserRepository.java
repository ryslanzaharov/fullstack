package com.auth.authorization.repository;

import com.auth.authorization.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(int userId);

    @Query("select u from User u where u.username=:username and u.password=:password")
    Optional<User> getByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("select u from User u where u.username=:username")
    Optional<User> getByUserName(@Param("username") String username);

}
