package de.qaware.neinhorn.coronazahlen;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VaccinationsResponseData {

    @JsonProperty("administeredVaccinations")
    long administeredVaccinations;

    @JsonProperty("vaccinated")
    long vaccinated;

    @JsonProperty("quote")
    BigDecimal quote;

}
