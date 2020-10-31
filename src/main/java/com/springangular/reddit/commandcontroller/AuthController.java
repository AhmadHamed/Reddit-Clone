package com.springangular.reddit.commandcontroller;

import com.springangular.reddit.dto.RegisterRequest;
import com.springangular.reddit.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
    authService.signup(registerRequest);

    return new ResponseEntity<>("Registration success!", HttpStatus.OK);
  }

  @GetMapping("/accountactivation/{userToken}")
  public ResponseEntity<String> activateAccount(@PathVariable String userToken) {
    authService.activateAccount(userToken);

    return new ResponseEntity<>("Activation success!", HttpStatus.OK);
  }
}
