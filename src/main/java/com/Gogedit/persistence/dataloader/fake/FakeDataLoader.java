package com.Gogedit.persistence.dataloader.fake;

import com.Gogedit.persistence.entity.Comment;
import com.Gogedit.persistence.entity.Community;
import com.Gogedit.persistence.entity.Post;
import com.Gogedit.persistence.repository.CommentRepository;
import com.Gogedit.persistence.repository.CommunityRepository;
import com.Gogedit.persistence.repository.PostRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FakeDataLoader implements CommandLineRunner {
  private final CommunityRepository communityRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  Faker faker = new Faker();

  @Autowired
  public FakeDataLoader(
      CommunityRepository communityRepository,
      PostRepository postRepository,
      CommentRepository commentRepository) {
    this.communityRepository = communityRepository;
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  int getRandomNumber(int upTo) {
    return faker.random().nextInt(upTo);
  }

  String getRandomText() {
    return String.join("\n\n", faker.lorem().paragraphs(getRandomNumber(5)));
  }

  @Override
  public void run(String... args) throws Exception {

    String[] communityNames = {
      "react",
      "islam",
      "cscareerquestions",
      "NBA",
      "NoStupidQuestions",
      "ELI5",
      "finance",
      "webdev",
      "java",
      "golang",
      "memes"
    };
    for (int i = 0; i < communityNames.length; i++) {
      Community community = new Community(communityNames[i], faker.lorem().sentence());
      communityRepository.save(community);

      int numPosts = getRandomNumber(50);
      for (int j = 0; j < numPosts; j++) {
        Post post = new Post(faker.lorem().sentence() + "?", getRandomText(), community);
        //      community.getPosts().add(post);
        postRepository.save(post);

        int numComments = getRandomNumber(50);

        for (int k = 0; k < numComments; k++) {
          Comment comment =
              new Comment(getRandomText(), faker.funnyName().name().replaceAll("\\s", ""), post);
          commentRepository.save(comment);
        }
      }
    }
  }
}
