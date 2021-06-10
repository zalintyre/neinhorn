package de.qaware.neinhorn.coronazahlen;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

@Data
public class CountryStatisticsResponse {

    @JsonbProperty("cases")
    long cases;

    @JsonbProperty("deaths")
    long deaths;

    @JsonbProperty("recovered")
    long recovered;

    @JsonbProperty("weekIncidence")
    BigDecimal weekIncidence;

    @JsonbProperty("casesPer100k")
    BigDecimal casesPer100k;

    @JsonbProperty("casesPerWeek")
    long casesPerWeek;

    @JsonbProperty("delta")
    Delta delta;

    @JsonbProperty("r")
    RValue r;

    @JsonbProperty("meta")
    MetaInfo metaInfo;
}
