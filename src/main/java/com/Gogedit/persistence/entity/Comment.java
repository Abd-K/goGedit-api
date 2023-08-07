package com.Gogedit.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(length = 10_000, nullable = false)
  private String text;
  private String author;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Post post;

  @ManyToOne
  private Comment parentComment;

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
  private Set<Comment> replies = new HashSet<>();
}
