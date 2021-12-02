package org.example.springbootrestsecurity.model;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User {
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
}