package com.springangular.reddit.repositories;

import com.springangular.reddit.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRep extends JpaRepository<Vote, Long> {}
