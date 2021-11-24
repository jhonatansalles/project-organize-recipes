package br.com.vibbra.organizerecipes.model.entity.expenses;

import br.com.vibbra.organizerecipes.base.BaseEntity;
import br.com.vibbra.organizerecipes.model.entity.categories.Categories;
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
@Table(name = "TB_EXPENSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Expenses extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("expense_id")
    private Long id;

    private BigDecimal amount;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Categories category;

    @Transient
    @JsonProperty("customer_id")
    private transient Long customerId;
}
