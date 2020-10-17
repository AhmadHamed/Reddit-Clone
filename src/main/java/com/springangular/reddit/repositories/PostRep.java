package com.springangular.reddit.repositories;

import com.springangular.reddit.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRep extends JpaRepository<Post, Long> {}
