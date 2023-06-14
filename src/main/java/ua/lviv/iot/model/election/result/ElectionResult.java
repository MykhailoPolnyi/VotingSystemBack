package ua.lviv.iot.model.election.result;

import lombok.*;
import ua.lviv.iot.model.election.candidate.Candidate;
import ua.lviv.iot.model.user.User;

import javax.persistence.*;

@Entity
@IdClass(ElectionResultId.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResult {
    @Id
    @ManyToOne
    @JoinColumn(name = "elector_id", referencedColumnName = "id")
    private User elector;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    @Column(nullable = false)
    private Integer voteNumber;
}
