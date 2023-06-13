package ua.lviv.iot.model.election;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.util.Date;

@Data
@SuperBuilder
public class ElectionDto {
    private Integer id;
    private String name;
    private String description;
    private Integer voteCount;
    private Integer availableVotes;
    private Integer localityType;
    private AddressDto localityAddress;
    private Boolean hasRetract;
    private Integer minAge;
    private Integer maxAge;
    private Date startDate;
    private Date endDate;
}
