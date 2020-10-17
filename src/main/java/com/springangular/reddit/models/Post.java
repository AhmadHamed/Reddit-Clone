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
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  @NotBlank(message = "Cannot be empty or null")
  private String postName;

  @Nullable private String url;

  @Lob
  @Nullable private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId",referencedColumnName = "userId")
  private User user;

  private Instant creationDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id",referencedColumnName = "id")
  private Subreddit subreddit;
}
