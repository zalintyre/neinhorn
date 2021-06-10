package de.qaware.neinhorn.coronazahlen;


import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

@Data
public class VaccinationsResponseData {

    @JsonbProperty("administeredVaccinations")
    long administeredVaccinations;

    @JsonbProperty("vaccinated")
    long vaccinated;

    @JsonbProperty("quote")
    BigDecimal quote;

}
