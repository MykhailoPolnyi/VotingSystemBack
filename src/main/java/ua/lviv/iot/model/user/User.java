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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false)
    protected String firstName;
    @Column(nullable = false)
    protected String secondName;
    @Column(nullable = false, unique = true)
    protected String identityCode;
    @Column(nullable = false)
    protected String emailAddress;
    @Column(nullable = false)
    protected String phoneNumber;
    @Column(nullable = false, columnDefinition = "DATE")
    protected LocalDate birthDate;
    @Enumerated(EnumType.ORDINAL)
    protected Sex sex;
    @ManyToOne
    @JoinColumn(name = "address_id")
    protected Address address;
    @OneToMany(mappedBy = "elector")
    private List<ElectionResult> electionResultAssociationList;

    public Integer getAge() {
        return Period
                .between(birthDate, LocalDate.now())
                .getYears();
    }
}