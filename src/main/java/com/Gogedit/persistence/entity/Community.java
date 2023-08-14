package com.Gogedit.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
    indexes = @Index(name = "index_name", columnList = "name")
)
public class Community {


  @Id
  @Column(nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(nullable = false)
  private String description;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate = LocalDateTime.now();

  @OneToMany(mappedBy = "community")
  private List<Post> posts = new ArrayList<>();

  public Community(String name, String description) {
    this.name = name;
    this.description = description;
  }


}
