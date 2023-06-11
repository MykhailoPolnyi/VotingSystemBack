package ua.lviv.iot.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.election.Election;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Admin extends User {
    private List<Election> ownedElectionList;
}