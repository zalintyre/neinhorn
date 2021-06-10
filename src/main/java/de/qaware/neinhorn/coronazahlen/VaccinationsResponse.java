package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VaccinationsResponse {
    @JsonProperty("data")
    VaccinationsResponseData vaccinationsResponseData;
}
