package ua.lviv.iot.model.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.result.ElectionResult;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false, length = 40)
    protected String firstName;
    @Column(nullable = false, length = 40)
    protected String secondName;
    @Column(nullable = false, length = 10, unique = true)
    protected String identityCode;
    @Column(length = 40)
    protected String emailAddress;
    @Column(length = 10)
    protected String phoneNumber;
    @Column(nullable = false)
    protected Date birthDate;
    @ManyToOne
    @JoinColumn(nullable = false, name = "address_id")
    protected Address address;
    @Enumerated(EnumType.ORDINAL)
    protected Sex sex;
    @OneToMany(mappedBy = "elector")
    private List<ElectionResult> electionResultAssociationList;
}