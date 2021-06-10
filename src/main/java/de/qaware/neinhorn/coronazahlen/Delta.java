package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Delta {

    @JsonProperty("cases")
    long cases;

    @JsonProperty("deaths")
    long deaths;

    @JsonProperty("recovered")
    long recovered;
}
