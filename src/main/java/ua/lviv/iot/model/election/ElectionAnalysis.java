package ua.lviv.iot.model.election;

import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ElectionAnalysis {
    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String location;
    private String candidate;
    private Integer voteCount;
}
