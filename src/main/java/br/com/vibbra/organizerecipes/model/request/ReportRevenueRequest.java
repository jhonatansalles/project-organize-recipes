package br.com.vibbra.organizerecipes.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRevenueRequest {

    @JsonProperty("fiscal_year")
    private Integer fiscalYear;
}