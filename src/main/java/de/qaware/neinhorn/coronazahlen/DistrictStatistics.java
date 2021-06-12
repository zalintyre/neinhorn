package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;

@Data
@RegisterForReflection
public class DistrictStatistics {

    @JsonProperty("ags")
    String districtKey;

    @JsonProperty("name")
    String name;

    @JsonProperty("county")
    String county;

    @JsonProperty("weekIncidence")
    BigDecimal weekIncidence;

    @JsonProperty("delta")
    Delta delta;

}
