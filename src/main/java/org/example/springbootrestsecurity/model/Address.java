package org.example.springbootrestsecurity.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_address")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_address")
    private Long id_address;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private Integer house;

    public Address(String city, String street, Integer house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }
}
