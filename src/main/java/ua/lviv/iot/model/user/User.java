package ua.lviv.iot.model.user;

import lombok.*;
import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.result.ElectionResult;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(length = 16)
    protected String phoneNumber;
    @Column(nullable = false, columnDefinition = "DATE")
    protected LocalDate birthDate;
    @Enumerated(EnumType.ORDINAL)
    protected Sex sex;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "address_id")
    protected Address address;
    @OneToMany(mappedBy = "elector")
    private List<ElectionResult> electionResultAssociationList;

    public Integer getAge() {
        return Period
                .between(birthDate, LocalDate.now())
                .getYears();
    }
}