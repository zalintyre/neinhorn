package de.qaware.neinhorn.rest;

import de.qaware.neinhorn.statistics.DistrictStatisticsData;
import de.qaware.neinhorn.statistics.StatisticsData;
import de.qaware.neinhorn.vaccinations.VaccinationsData;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CovidData {
    StatisticsData statisticsData;
    DistrictStatisticsData districtStatisticsData;
    VaccinationsData vaccinationsData;
}
