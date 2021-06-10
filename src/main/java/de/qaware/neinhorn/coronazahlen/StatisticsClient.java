package de.qaware.neinhorn.coronazahlen;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RegisterRestClient
@Produces("application/json")
public interface StatisticsClient {

    @GET
    @Path("/germany")
    CountryStatisticsResponse getStatisticsForGermany();

}
