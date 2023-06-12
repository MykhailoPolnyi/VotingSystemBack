package ua.lviv.iot.model.election;

import lombok.*;
import ua.lviv.iot.model.address.Address;
import ua.lviv.iot.model.election.candidate.Candidate;
import ua.lviv.iot.model.user.Admin;

import javax.persistence.*;
import java.util.Date;
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
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Builder.Default
    @Column(nullable = false)
    private Boolean hasRetract = false;
    @Builder.Default
    @Column(nullable = false)
    private Integer minAge = 18;
    private Integer maxAge;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "election")
    private List<Candidate> candidateAssociationList;
}
