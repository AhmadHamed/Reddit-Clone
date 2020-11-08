package com.springangular.reddit.services;

import com.springangular.reddit.models.User;
import com.springangular.reddit.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class RedditUserDetailsService implements UserDetailsService {
  private final String USER = "USER";
  private final UserRep userRep;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRep.findByUserName(userName);
    if (isNull(user)) {
      throw new UsernameNotFoundException(userName + " was not found!");
    } else {
      return new org.springframework.security.core.userdetails.User(
              user.getUserName(),
              user.getPassword(),
              user.isEnabled(),
              true,
              true,
              true,
              getAuthorities(USER));
    }
  }

  private Collection<? extends GrantedAuthority> getAuthorities(String role) {
    return Collections.singletonList(new SimpleGrantedAuthority(role));
  }
}
