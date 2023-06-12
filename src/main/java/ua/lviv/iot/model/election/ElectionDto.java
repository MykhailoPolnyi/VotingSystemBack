package ua.lviv.iot.model.election;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.util.Date;

@Data
@SuperBuilder
public class ElectionDto {
    private Integer id;
    @NonNull
    private String name;
    private String description;
    private Integer votesCount;
    @NonNull
    private Integer availableVotes;
    @NonNull
    private Integer localityType;
    private AddressDto localityAddress;
    @NonNull
    private Boolean hasRetract;
    @NonNull
    private Integer minAge;
    private Integer maxAge;
    @NonNull
    private Date startDate;
    @NonNull
    private Date endDate;
}
