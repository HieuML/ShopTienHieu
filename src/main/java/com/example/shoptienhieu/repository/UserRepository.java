package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    /**
     * get all user
     * @return
     */
    List<User> findAll();

    /**
     * find user by firstName
     * @param firstName
     * @return
     */
    @Query("select u from User  u where u.firstName like %:firstName%")
    List<User> findUsersByFirstNameContaining(@Param("firstName") String firstName);

    /**
     * find user by username
     * @param username
     * @return
     */
    Optional<User> findUserByUsername(String username);
}
