package com.Gogedit.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
 
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;
  @Column(nullable = false)
  private String title;
  @Column(length = 10_000)
  private String body;
  @ManyToOne
  @JoinColumn(nullable = false)
  private Community community;

   @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  Set<Comment> comments = new HashSet<>();

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate = LocalDateTime.now();

  public Post(String title, Community community) {
    this.title = title;
    this.community = community;
  }

  public Post(String title, String body, Community community) {
    this.title = title;
    this.body = body;
    this.community = community;
  }
}
