package ua.lviv.iot.model.address;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String homeAddress;
    private String city;
    private String district;
    private String state;
    private String postalCode;
}