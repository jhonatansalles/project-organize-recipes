package br.com.vibbra.organizerecipes.model.response.reports;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ReportRevenueCustomerResponse {

    private List<RevenueCustomerItem> revenue;

    @JsonProperty("max_revenue_amount")
    private BigDecimal maxRevenueAmount;
}