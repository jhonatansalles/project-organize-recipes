package br.com.vibbra.organizerecipes.model.entity.settings;

import br.com.vibbra.organizerecipes.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_SETTINGS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Settings extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("settings_id")
    private Long id;

    @JsonProperty("max_revenue_amount")
    @Column( name = "max_revenue_amount")
    private BigDecimal maxRevenueAmount;

    @JsonProperty("sms_notification")
    @Column( name = "sms_notification")
    private Boolean smsNotification;

    @JsonProperty("email_notification")
    @Column( name = "email_notification")
    private Boolean emailNotification;
}
