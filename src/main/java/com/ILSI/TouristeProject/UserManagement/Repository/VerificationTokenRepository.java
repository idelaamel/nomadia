package com.ILSI.TouristeProject.UserManagement.Repository;

import com.ILSI.TouristeProject.UserManagement.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


    VerificationToken findByToken(String token);
}
