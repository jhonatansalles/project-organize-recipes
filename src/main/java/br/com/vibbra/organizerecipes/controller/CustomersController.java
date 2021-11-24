package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.entity.customers.Customers;
import br.com.vibbra.organizerecipes.model.response.CustomerListResponse;
import br.com.vibbra.organizerecipes.model.response.CustomerResponse;
import br.com.vibbra.organizerecipes.service.customers.CustomersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customers")
@PreAuthorize("hasRole('USER')")
public class CustomersController {

    @Autowired
    CustomersService customersService;

    @ApiOperation(value = "Retrieve list of customers by NAME and CNPJ.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerListResponse> list(@RequestParam String name, @RequestParam String cnpj) {
        return ResponseEntity.ok(customersService.listCustomers(name, cnpj));
    }

    @ApiOperation(value = "Retrieve customer data by ID.")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(customersService.getCustomer(id));
    }

    @ApiOperation(value = "Create a specific customer with the data entered.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customers> create(@RequestBody Customers customer) {
        return new ResponseEntity<>(customersService.createCustomer(customer), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change data for a specific customer.")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Customers customer) {
        customersService.updateCustomer(id, customer);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Change data for a customer and archives.")
    @PutMapping(path = "/{id}/archives", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomerArchives(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}