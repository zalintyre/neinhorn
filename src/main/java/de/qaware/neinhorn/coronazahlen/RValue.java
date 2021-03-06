package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RegisterForReflection
public class RValue {

    @JsonProperty("value")
    BigDecimal value;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDate date;
}
