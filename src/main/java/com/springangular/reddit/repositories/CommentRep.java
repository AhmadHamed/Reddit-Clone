package com.springangular.reddit.repositories;

import com.springangular.reddit.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRep extends JpaRepository<Comment, Long> {}
