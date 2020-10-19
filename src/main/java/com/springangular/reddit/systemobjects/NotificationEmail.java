package com.springangular.reddit.systemobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {

  private String subject;
  private String sender;
  private String recipient;
  private String body;

  public void setSender() {
    this.sender = "ahmad_hamed95@yahoo.com";
  }
}
