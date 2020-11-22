package ru.dshishkaryan.sovkombank.domain.invitesDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InvitesDto {
    private List<String> phoneNumbers;
    private String message;
}
