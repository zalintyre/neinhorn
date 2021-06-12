package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;

@Data
@RegisterForReflection
public class SecondVaccination {

    @JsonProperty("vaccinated")
    long vaccinated;

    @JsonProperty("quote")
    BigDecimal quote;
}
