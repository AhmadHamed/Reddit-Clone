package com.springangular.reddit.security;

import com.springangular.reddit.services.RedditUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final String AUTHENTICATION = "Authentication";
  private final String BEARER = "Bearer";
  private JwtProvider jwtProvider;
  private RedditUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws IOException, ServletException {
    String jwtFromRequest = getJwtFromRequest(request);
    if (!jwtFromRequest.isEmpty() && jwtProvider.validateJwt(jwtFromRequest)) {
      String userName = jwtProvider.getUserNameFromJwt(jwtFromRequest);

      UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

      UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());

      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHENTICATION);
    if (!bearerToken.isEmpty() && bearerToken.startsWith(BEARER)) {
      bearerToken = bearerToken.substring(7);
    }
    return bearerToken;
  }

  public void setJwtProvider(JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  public void setUserDetailsService(RedditUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
}
