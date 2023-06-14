package ua.lviv.iot.model.election.candidate;

import lombok.*;
import ua.lviv.iot.model.election.Election;
import ua.lviv.iot.model.election.result.ElectionResult;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;
    @OneToMany(mappedBy = "candidate")
    private List<ElectionResult> electionResultAssociationList;
}
