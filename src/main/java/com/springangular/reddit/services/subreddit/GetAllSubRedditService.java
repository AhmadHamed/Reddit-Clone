package com.springangular.reddit.services.subreddit;

import com.springangular.reddit.dto.SubredditDto;
import com.springangular.reddit.models.Subreddit;
import com.springangular.reddit.repositories.SubredditRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class GetAllSubRedditService {

  private final SubredditRep subredditRep;

  @Transactional(readOnly = true)
  public List<SubredditDto> getAll() {
    return subredditRep.findAll().stream().map(this::ConstructSubredditDto).collect(toList());
  }

  private SubredditDto ConstructSubredditDto(Subreddit subreddit) {
    return SubredditDto.builder()
            .id(subreddit.getId())
            .name(subreddit.getName())
            .description(subreddit.getDescription())
            .numberOfPosts(subreddit.getPosts().size())
            .build();
  }
}
