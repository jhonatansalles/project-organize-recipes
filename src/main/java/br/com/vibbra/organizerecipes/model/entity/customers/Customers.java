package br.com.vibbra.organizerecipes.model.entity.customers;

import br.com.vibbra.organizerecipes.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_CUSTOMERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Customers extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("customer_id")
    private Long id;

    private String cnpj;

    @JsonProperty("commercial_name")
    @Column(name = "commercial_name")
    private String commercialName;

    @JsonProperty("legal_name")
    @Column(name = "legal_name")
    private String legalName;
}