package com.springangular.reddit.repositories;

import com.springangular.reddit.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRep extends JpaRepository<VerificationToken, Long> {
  VerificationToken findOneByToken(String token);
}
