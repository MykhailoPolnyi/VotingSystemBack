package ua.lviv.iot.model.election;

import lombok.*;
import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.candidate.Candidate;
import ua.lviv.iot.model.user.Admin;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Election {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Integer availableVotes;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private LocalityType localityType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address localityAddress;
    @Builder.Default
    @Column(nullable = false)
    private Boolean hasRetract = false;
    @Builder.Default
    @Column(nullable = false)
    private Integer minAge = 18;
    private Integer maxAge;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate startDate;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "election")
    private List<Candidate> candidateAssociationList;
}