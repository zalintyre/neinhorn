package de.qaware.neinhorn.rest;

import de.qaware.neinhorn.statistics.StatisticsData;
import de.qaware.neinhorn.vaccinations.VaccinationsData;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CovidData {
    StatisticsData statisticsData;
    VaccinationsData vaccinationsData;
}
