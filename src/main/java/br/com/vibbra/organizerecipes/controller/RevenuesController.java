package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.entity.revenues.Revenues;
import br.com.vibbra.organizerecipes.service.revenues.RevenuesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/revenues")
@PreAuthorize("hasRole('USER')")
public class RevenuesController {

    @Autowired
    RevenuesService revenuesService;

    @ApiOperation(value = "Create a specific revenue with the data entered.")
    @PostMapping(path = "/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Revenues> create(@PathVariable Long customerID, @RequestBody Revenues revenue) {
        return new ResponseEntity<>(revenuesService.createRevenue(customerID, revenue), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change data for a specific revenue.")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Revenues revenue) {
        revenuesService.updateRevenue(id, revenue);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete a specific revenue.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        revenuesService.deleteRevenue(id);
        return ResponseEntity.noContent().build();
    }
}