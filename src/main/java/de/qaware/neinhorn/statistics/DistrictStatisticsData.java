package de.qaware.neinhorn.statistics;

import de.qaware.neinhorn.coronazahlen.DistrictStatistics;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class DistrictStatisticsData {
    String name;
    String county;
    BigDecimal currentWeekIncidence;
    long deltaCases;

    public static DistrictStatisticsData of(DistrictStatistics districtStatistics) {
        return DistrictStatisticsData.builder()
            .name(districtStatistics.getName())
            .county(districtStatistics.getCounty())
            .currentWeekIncidence(districtStatistics.getWeekIncidence())
            .deltaCases(districtStatistics.getDelta().getCases())
            .build();
    }
}
