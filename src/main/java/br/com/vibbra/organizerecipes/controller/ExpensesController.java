package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.entity.expenses.Expenses;
import br.com.vibbra.organizerecipes.service.expenses.ExpensesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/expenses")
@PreAuthorize("hasRole('USER')")
public class ExpensesController {

    @Autowired
    ExpensesService expenseService;

    @ApiOperation(value = "Create a specific expense with the data entered.")
    @PostMapping(path = "/{categoryID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> create(@PathVariable Long categoryID, @RequestBody Expenses expense) {
        return new ResponseEntity<>(expenseService.createExpense(categoryID, expense), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change data for a specific expense.")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Expenses expense) {
        expenseService.updateExpense(id, expense);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete a specific expense.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}