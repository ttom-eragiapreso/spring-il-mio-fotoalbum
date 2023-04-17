package org.photoalbum.photoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_messages")
public class UserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull
    @NotEmpty(message = "Content cannot be empty")
    private String content;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
