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
    BigDecimal firstDoseQuote;
    BigDecimal secondDoseQuote;

    public static VaccinationsData of(VaccinationsResponseData vaccinationsResponseData) {
        return VaccinationsData.builder()
            .administeredVaccinations(vaccinationsResponseData.getAdministeredVaccinations())
            .vaccinated(vaccinationsResponseData.getVaccinated())
            .firstDoseQuote(vaccinationsResponseData.getQuote())
            .secondDoseQuote(vaccinationsResponseData.getSecondVaccination().getQuote())
            .build();
    }
}
