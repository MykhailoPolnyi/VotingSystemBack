package ua.lviv.iot.model.election.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ElectionResultId implements Serializable {
    private Integer elector;
    private Integer candidate;
}
