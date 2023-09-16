package com.Gogedit.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @OneToMany
    private List<Post> posts;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
