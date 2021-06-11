package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;

@Data
@RegisterForReflection
public class CountryStatisticsResponse {

    @JsonProperty("cases")
    long cases;

    @JsonProperty("deaths")
    long deaths;

    @JsonProperty("recovered")
    long recovered;

    @JsonProperty("weekIncidence")
    BigDecimal weekIncidence;

    @JsonProperty("casesPer100k")
    BigDecimal casesPer100k;

    @JsonProperty("casesPerWeek")
    long casesPerWeek;

    @JsonProperty("delta")
    Delta delta;

    @JsonProperty("r")
    RValue r;

    @JsonProperty("meta")
    MetaInfo metaInfo;
}
