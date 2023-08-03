package com.Gogedit.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
  private String description;

  @OneToMany(mappedBy = "community")
  private List<Post> posts = new ArrayList<>();
}
