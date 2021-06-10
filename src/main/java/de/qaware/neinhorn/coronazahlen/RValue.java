package de.qaware.neinhorn.coronazahlen;

import lombok.Data;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RValue {

    @JsonbProperty("value")
    BigDecimal value;

    @JsonbProperty("date")
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDate date;
}
