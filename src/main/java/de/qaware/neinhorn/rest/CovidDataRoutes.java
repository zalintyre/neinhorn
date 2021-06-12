package de.qaware.neinhorn.rest;

import de.qaware.neinhorn.coronazahlen.CountryStatisticsResponse;
import de.qaware.neinhorn.coronazahlen.DistrictStatistics;
import de.qaware.neinhorn.coronazahlen.DistrictsStatistcsResponse;
import de.qaware.neinhorn.coronazahlen.DistrictsStatisticsResponseData;
import de.qaware.neinhorn.coronazahlen.StatisticsClient;
import de.qaware.neinhorn.coronazahlen.VaccinationsClient;
import de.qaware.neinhorn.coronazahlen.VaccinationsResponse;
import de.qaware.neinhorn.statistics.DistrictStatisticsData;
import de.qaware.neinhorn.statistics.StatisticsData;
import de.qaware.neinhorn.vaccinations.VaccinationsData;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.RoutingExchange;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@Slf4j
@ApplicationScoped
@RouteBase(produces = "application/json")
public class CovidDataRoutes {

    private static final String DEFAULT_COUNTY_CODE = "09163";

    @Inject
    @RestClient
    VaccinationsClient vaccionationsClient;

    @Inject
    @RestClient
    StatisticsClient statisticsClient;

    @Route(path = "/coviddata")
    Uni<CovidData> retrieveCovidData(RoutingExchange rc) {
        Uni<VaccinationsResponse> vaccinationsResponse = vaccionationsClient.getVaccinations();
        Uni<CountryStatisticsResponse> germanStatisticsResponse = statisticsClient.getStatisticsForGermany();
        Uni<DistrictsStatistcsResponse> districtsStatisticsResponse = statisticsClient.getDistrictStatistics();

        Uni<VaccinationsData> vaccinationsData = vaccinationsResponse
            .map(VaccinationsResponse::getVaccinationsResponseData)
            .map(VaccinationsData::of);

        Uni<StatisticsData> statisticsData = germanStatisticsResponse
            .map(StatisticsData::of);

        String countyCode = rc.getParam("county").orElse(DEFAULT_COUNTY_CODE);

        Uni<Map<String, DistrictStatistics>> districtStatisticsMap = districtsStatisticsResponse
            .map(DistrictsStatistcsResponse::getDistrictsStatisticsResponseData)
            .map(DistrictsStatisticsResponseData::getDistrictStatisticsMap);

        Uni<DistrictStatisticsData> districtStatisticsData = districtStatisticsMap
            .map(map -> map.get(countyCode))
            .map(DistrictStatisticsData::of);

        return Uni.combine()
            .all()
            .unis(vaccinationsData, statisticsData, districtStatisticsData)
            .combinedWith((vaccinations, statistics, districtStatistics) -> CovidData.builder()
                .vaccinationsData(vaccinations)
                .statisticsData(statistics)
                .districtStatisticsData(districtStatistics)
                .build());
    }

}
