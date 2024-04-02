package org.example.chinesfoodcafe.repository;

import org.example.chinesfoodcafe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);

}
