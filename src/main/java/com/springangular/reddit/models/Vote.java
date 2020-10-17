package com.springangular.reddit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long voteId;

  private VoteType voteType;

  @NotBlank(message = "Cannot be empty or null")
  private String postName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postId",referencedColumnName = "postId")
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId",referencedColumnName = "userId")
  private User user;
}
