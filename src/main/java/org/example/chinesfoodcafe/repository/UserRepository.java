package org.example.chinesfoodcafe.repository;

import org.example.chinesfoodcafe.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "select * from users where id=:id")
    User findUserById(Long id);

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "select * from users")
    Page<User> getAllUsers(PageRequest pageRequest);

    boolean existsByEmail(String email);

}
