package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message ="Username is mandatory")
    @Column(nullable = false, unique = true, length = 125)
    private String username;

    @NotBlank(message ="Password is mandatory")
    @Length(min = 8, max = 125, message = "Password not conform")
    @Pattern(
            regexp ="^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;\"',.<>/?]).{8,}$",
            message = "Min 8 chars, 1 uppercase, 1 number, 1 symbol")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    @Column(length = 125)
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    @Column(length = 20)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
