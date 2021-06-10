package de.qaware.neinhorn.vaccinations;

import de.qaware.neinhorn.coronazahlen.VaccinationsResponseData;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class VaccinationsData {
    long administeredVaccinations;
    long vaccinated;
    BigDecimal quote;

    public static VaccinationsData of(VaccinationsResponseData vaccinationsResponseData) {
        return VaccinationsData.builder()
                .administeredVaccinations(vaccinationsResponseData.getAdministeredVaccinations())
                .vaccinated(vaccinationsResponseData.getVaccinated())
                .quote(vaccinationsResponseData.getQuote())
                .build();
    }
}
