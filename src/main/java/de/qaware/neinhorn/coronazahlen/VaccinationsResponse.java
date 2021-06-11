package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class VaccinationsResponse {
    @JsonProperty("data")
    VaccinationsResponseData vaccinationsResponseData;
}
