package org.example.springbootrestsecurity.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    private  boolean isActive;

//    private  List<SimpleGrantedAuthority> authorities;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address_user")
    private Address address;

    public User(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String firstName, String lastName, Integer age, String password, Role role, Status status, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.role = role;
        this.status = status;
        this.address = address;
    }

    public User(String firstName, String lastName, Integer age, String password, Role role, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User(Long id, String firstName, String lastName, Integer age, String password, Role role, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.role = role;
        this.status = status;
    }

        @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    /*метод для преобразования сущности юзера в юзера понятного для секьюрити*/

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getFirstName(), user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}