package de.qaware.neinhorn.coronazahlen;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Data
public class VaccinationsResponse {
    @JsonbProperty("data")
    VaccinationsResponseData vaccinationsResponseData;
}
