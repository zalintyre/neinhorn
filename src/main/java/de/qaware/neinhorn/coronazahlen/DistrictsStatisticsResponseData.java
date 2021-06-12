package de.qaware.neinhorn.coronazahlen;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@RegisterForReflection
public class DistrictsStatisticsResponseData {

    Map<String, DistrictStatistics> districtStatisticsMap;

    public DistrictsStatisticsResponseData() {
        this.districtStatisticsMap = new HashMap<>();
    }

    @JsonAnySetter
    public void deserializeMapProperty(String key, DistrictStatistics value) {
        districtStatisticsMap.put(key, value);
    }
}
