package com.techytown.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotEmpty
  @Size(max = 20)
  @Column(unique = true)
  private String username;

  @NotEmpty
  @Email(message = "You must enter Valid Email")
  @Column(unique = true)
  private String email;

  @NotEmpty
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
  
  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL)
  private Cart cart;
  
  @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",targetEntity = Card.class)
  private List<Card> cards = new ArrayList<>();
  
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
  
}
