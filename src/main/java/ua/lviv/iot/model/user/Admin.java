package ua.lviv.iot.model.user;

import lombok.*;
import ua.lviv.iot.model.election.Election;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin{
    @Id
    @Column(name = "user_id")
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "admin")
    private List<Election> ownedElectionAssociationList;
}