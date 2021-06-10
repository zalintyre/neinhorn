package de.qaware.neinhorn.rest;

import de.qaware.neinhorn.coronazahlen.CountryStatisticsResponse;
import de.qaware.neinhorn.coronazahlen.StatisticsClient;
import de.qaware.neinhorn.coronazahlen.VaccinationsClient;
import de.qaware.neinhorn.coronazahlen.VaccinationsResponse;
import de.qaware.neinhorn.statistics.StatisticsData;
import de.qaware.neinhorn.vaccinations.VaccinationsData;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RouteBase(produces = "application/json")
public class CovidDataRoutes {

    @Inject
    @RestClient
    VaccinationsClient vaccionationsClient;

    @Inject
    @RestClient
    StatisticsClient statisticsClient;

    @Route(path = "/coviddata")
    Uni<CovidData> retrieveCovidData() {
        Uni<VaccinationsResponse> vaccinationsResponse = vaccionationsClient.getVaccinations();
        Uni<CountryStatisticsResponse> germanStatisticsResponse = statisticsClient.getStatisticsForGermany();

        Uni<VaccinationsData> vaccinationsData = vaccinationsResponse
                .map(VaccinationsResponse::getVaccinationsResponseData)
                .map(VaccinationsData::of);

        Uni<StatisticsData> statisticsData = germanStatisticsResponse
                .map(StatisticsData::of);

        return Uni.combine()
                .all()
                .unis(vaccinationsData, statisticsData)
                .combinedWith((vaccinations, statistics) -> CovidData.builder()
                        .vaccinationsData(vaccinations)
                        .statisticsData(statistics)
                        .build());
    }

}
