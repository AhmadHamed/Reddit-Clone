package com.springangular.reddit.repositories;

import com.springangular.reddit.models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRep extends JpaRepository<Subreddit, Long> {}
