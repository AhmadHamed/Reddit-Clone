package com.springangular.reddit.services.subreddit;

import com.springangular.reddit.dto.SubredditDto;
import com.springangular.reddit.models.Subreddit;
import com.springangular.reddit.repositories.SubredditRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateSubRedditService {

  private final SubredditRep subredditRep;

  @Transactional
  public void save(SubredditDto subredditDto) {
    Subreddit subreddit = constructSubReddit(subredditDto);
    subredditRep.save(subreddit);
  }

  private Subreddit constructSubReddit(SubredditDto subredditDto) {
    return Subreddit.builder()
            .name(subredditDto.getName())
            .description(subredditDto.getDescription())
            .build();
  }
}
