package ua.lviv.iot.model.election;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import ua.lviv.iot.model.address.AddressDto;

import java.time.LocalDate;

@Data
@SuperBuilder
public class ElectionDto {
    private Integer id;
    @NonNull
    private String name;
    private String description;
    private Integer voteCount;
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
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;
}
