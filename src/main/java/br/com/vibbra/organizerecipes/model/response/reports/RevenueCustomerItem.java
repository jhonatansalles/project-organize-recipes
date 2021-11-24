package br.com.vibbra.organizerecipes.model.response.reports;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class RevenueCustomerItem {

    @JsonProperty("customer_name")
    private String customerName;

    private BigDecimal revenue;
}