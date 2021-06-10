package de.qaware.neinhorn.coronazahlen;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Data
public class Delta {

    @JsonbProperty("cases")
    long cases;

    @JsonbProperty("deaths")
    long deaths;

    @JsonbProperty("recovered")
    long recovered;
}
