package de.qaware.neinhorn.rest;

import de.qaware.neinhorn.coronazahlen.CountryStatisticsResponse;
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

        String countyCode = rc.getParam("county").orElse(DEFAULT_COUNTY_CODE);

        Uni<VaccinationsData> vaccinationsData = getVaccinationsData(vaccinationsResponse);
        Uni<StatisticsData> statisticsData = getStatisticsData(germanStatisticsResponse);
        Uni<DistrictStatisticsData> districtStatisticsData = getDistrictStatisticsData(districtsStatisticsResponse, countyCode);

        return Uni.combine()
            .all()
            .unis(vaccinationsData, statisticsData, districtStatisticsData)
            .combinedWith((vaccinations, statistics, districtStatistics) -> CovidData.builder()
                .vaccinationsData(vaccinations)
                .statisticsData(statistics)
                .districtStatisticsData(districtStatistics)
                .build());
    }

    private Uni<VaccinationsData> getVaccinationsData(Uni<VaccinationsResponse> vaccinationsResponse) {
        return vaccinationsResponse
            .map(VaccinationsResponse::getVaccinationsResponseData)
            .map(VaccinationsData::of);
    }

    private Uni<StatisticsData> getStatisticsData(Uni<CountryStatisticsResponse> germanStatisticsResponse) {
        return germanStatisticsResponse
            .map(StatisticsData::of);
    }

    private Uni<DistrictStatisticsData> getDistrictStatisticsData(Uni<DistrictsStatistcsResponse> districtsStatisticsResponse, String countyCode) {
        return districtsStatisticsResponse
            .map(DistrictsStatistcsResponse::getDistrictsStatisticsResponseData)
            .map(DistrictsStatisticsResponseData::getDistrictStatisticsMap)
            .map(map -> map.get(countyCode))
            .map(DistrictStatisticsData::of);
    }
}
