package br.com.vibbra.organizerecipes.model.entity.revenues;

import br.com.vibbra.organizerecipes.base.BaseEntity;
import br.com.vibbra.organizerecipes.model.entity.customers.Customers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_REVENUES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Revenues extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("revenue_id")
    private Long id;

    private BigDecimal amount;

    @JsonProperty("invoice_id")
    @Column(name = "invoice_id")
    private String invoiceId;

    private String description;

    @JsonProperty("accrual_date")
    @Column(name = "accrual_date")
    private LocalDateTime accrualDate;

    @JsonProperty("transaction_date")
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customers customer;
}
