package de.qaware.neinhorn.coronazahlen;

import lombok.Data;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDate;

@Data
public class MetaInfo {

    @JsonbProperty("source")
    String source;

    @JsonbProperty("contact")
    String contact;

    @JsonbProperty("info")
    String info;

    @JsonbProperty("lastUpdate")
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDate lastUpdate;

    @JsonbProperty("lastCheckedForUpdate")
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDate lastCheckedForUpdate;
}
