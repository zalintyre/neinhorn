package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MetaInfo {

    @JsonProperty("source")
    String source;

    @JsonProperty("contact")
    String contact;

    @JsonProperty("info")
    String info;

    @JsonProperty("lastUpdate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDate lastUpdate;

    @JsonProperty("lastCheckedForUpdate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDate lastCheckedForUpdate;
}
