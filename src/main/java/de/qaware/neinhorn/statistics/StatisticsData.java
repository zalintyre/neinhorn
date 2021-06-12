package de.qaware.neinhorn.statistics;

import de.qaware.neinhorn.coronazahlen.CountryStatisticsResponse;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class StatisticsData {
    BigDecimal currentRValue;
    BigDecimal currentWeekIncidence;
    long deltaCases;

    public static StatisticsData of(CountryStatisticsResponse countryStatisticsResponse) {
        return StatisticsData.builder()
            .currentRValue(countryStatisticsResponse.getR().getValue())
            .currentWeekIncidence(countryStatisticsResponse.getWeekIncidence())
            .deltaCases(countryStatisticsResponse.getDelta().getCases())
            .build();
    }
}
