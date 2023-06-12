package ua.lviv.iot.model.user;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.Address;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @NonNull
    @Column(nullable = false, length = 40)
    protected String firstName;
    @NonNull
    @Column(nullable = false, length = 40)
    protected String secondName;
    @NonNull
    @Column(nullable = false, length = 10, unique = true)
    protected String identityCode;
    @NonNull
    @Column(length = 40)
    protected String emailAddress;
    @NonNull
    @Column(length = 10)
    protected String phoneNumber;
    @NonNull
    @Column(nullable = false)
    protected Date birthDate;
    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false)
    protected Address address;
    @Enumerated(EnumType.ORDINAL)
    protected Sex sex;
}