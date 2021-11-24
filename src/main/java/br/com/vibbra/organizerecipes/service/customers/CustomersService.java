package br.com.vibbra.organizerecipes.service.customers;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.exception.NotFoundException;
import br.com.vibbra.organizerecipes.exception.ValidationException;
import br.com.vibbra.organizerecipes.model.entity.customers.Customers;
import br.com.vibbra.organizerecipes.model.response.CustomerListResponse;
import br.com.vibbra.organizerecipes.model.response.CustomerResponse;
import br.com.vibbra.organizerecipes.repository.customers.CustomersRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CustomersService extends BaseService {

    @Autowired
    CustomersRepository customersRepository;

    public CustomerListResponse listCustomers(String name, String cnpj) {
        List<Customers> customersList = customersRepository.findAllByNameAndCnpj(name, cnpj);
        return CustomerListResponse.builder()
                .count(customersList.size())
                .customers(customersList)
                .build();
    }

    public CustomerResponse getCustomer(Long id) {
        Optional<Customers> optionalCustomer = customersRepository.findById(id);
        return CustomerResponse.builder()
                .customer(optionalCustomer.orElseThrow(() -> new NotFoundException("Customer not found with -> ID: " + id)))
                .build();
    }

    public Customers createCustomer(Customers customer) {

        this.validateRequiredFields(this.returnMapRequiredFields(customer));

        if (BooleanUtils.isTrue(customersRepository.existsByCnpj(customer.getCnpj()))) {
            throw new ValidationException("There is already a customer with -> CNPJ: " + customer.getCnpj());
        }

        customer.setUserLastchange("API_CREATE_CUSTOMER");

        Customers savedCustomer = customersRepository.save(customer);

        return Customers.builder()
                .id(savedCustomer.getId())
                .build();
    }

    public void updateCustomer(Long id, Customers customer) {

        this.validateRequiredFields(this.returnMapRequiredFields(customer));

        Customers customerBD = customersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with -> ID: " + id));

        customerBD.setId(id);
        customerBD.setVersion(customerBD.getVersion());
        customerBD.setUserLastchange("API_UPDATE_CUSTOMER");

        customersRepository.save(customer);
    }

    private Map<String, Optional<?>> returnMapRequiredFields(Customers object) {
        return Map.of(
                "CNPJ", Optional.ofNullable(object).map(Customers::getCnpj),
                "Commercial Name", Optional.ofNullable(object).map(Customers::getCommercialName),
                "Legal Name", Optional.ofNullable(object).map(Customers::getLegalName)
        );
    }
}
