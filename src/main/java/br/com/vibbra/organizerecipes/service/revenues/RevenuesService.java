package br.com.vibbra.organizerecipes.service.revenues;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.exception.NotFoundException;
import br.com.vibbra.organizerecipes.model.entity.customers.Customers;
import br.com.vibbra.organizerecipes.model.entity.revenues.Revenues;
import br.com.vibbra.organizerecipes.repository.revenues.RevenuesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class RevenuesService extends BaseService {

    @Autowired
    RevenuesRepository revenueRepository;

    public Revenues createRevenue(Long customerID, Revenues revenue) {

        this.validateRequiredFields(this.returnMapRequiredFields(revenue));

        revenue.setCustomer(Customers.builder().id(customerID).build());
        revenue.setUserLastchange("API_CREATE_USER");

        Revenues savedRevenue = revenueRepository.save(revenue);

        return Revenues.builder()
                .id(savedRevenue.getId())
                .build();
    }

    public void updateRevenue(Long id, Revenues revenue) {

        this.validateRequiredFields(this.returnMapRequiredFields(revenue));

        Revenues revenueBD = revenueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Revenue not found with -> ID: " + id));

        revenue.setId(id);
        revenue.setVersion(revenueBD.getVersion());
        revenue.setUserLastchange("API_UPDATE_REVENUE");

        revenueRepository.save(revenue);
    }

    public void deleteRevenue(Long id) {
        revenueRepository.deleteById(id);
    }

    private Map<String, Optional<?>> returnMapRequiredFields(Revenues object) {
        return Map.of(
                "Amount", Optional.ofNullable(object).map(Revenues::getAmount),
                "Invoice ID", Optional.ofNullable(object).map(Revenues::getInvoiceId),
                "Description", Optional.ofNullable(object).map(Revenues::getDescription),
                "Accrual Date", Optional.ofNullable(object).map(Revenues::getAccrualDate),
                "Transaction Date", Optional.ofNullable(object).map(Revenues::getTransactionDate)
        );
    }
}
