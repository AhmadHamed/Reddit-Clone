package com.springangular.reddit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotBlank(message = "Username is required!")
  @Column(unique = true)
  private String userName;

  @NotBlank(message = "Password is required!")
  private String password;

  @Email
  @NotEmpty(message = "email is required!")
  private String email;

  private Instant creationDate;

  private boolean enabled;
}
