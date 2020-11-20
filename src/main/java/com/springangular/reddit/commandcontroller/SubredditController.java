package com.springangular.reddit.commandcontroller;

import com.springangular.reddit.dto.SubredditDto;
import com.springangular.reddit.services.subreddit.CreateSubRedditService;
import com.springangular.reddit.services.subreddit.GetAllSubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {

  private final CreateSubRedditService createSubRedditService;
  private final GetAllSubRedditService getAllSubRedditService;

  @PostMapping("/create")
  public ResponseEntity<String> createSubReddit(@RequestBody SubredditDto subredditDto) {
    createSubRedditService.save(subredditDto);
    return new ResponseEntity<>("Subreddit creation success!", HttpStatus.CREATED);
  }

  @GetMapping("/getall")
  public ResponseEntity<List<SubredditDto>> getAllSubReddits() {
    List<SubredditDto> subReddits = getAllSubRedditService.getAll();
    return new ResponseEntity<List<SubredditDto>>(subReddits, HttpStatus.OK);
  }
}
