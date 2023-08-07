package com.Gogedit.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
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

  @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
  private List<Post> posts = new ArrayList<>();
}
