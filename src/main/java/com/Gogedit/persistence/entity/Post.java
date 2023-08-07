package com.Gogedit.persistence.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@AllArgsConstructor
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
  private Instant createdDate = Instant.now();
}
