package br.com.vibbra.organizerecipes.service.expenses;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.exception.NotFoundException;
import br.com.vibbra.organizerecipes.model.entity.categories.Categories;
import br.com.vibbra.organizerecipes.model.entity.customers.Customers;
import br.com.vibbra.organizerecipes.model.entity.expenses.Expenses;
import br.com.vibbra.organizerecipes.repository.expenses.ExpensesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ExpensesService extends BaseService {

    @Autowired
    ExpensesRepository expensesRepository;

    public Expenses createExpense(Long categoryID, Expenses expense) {

        this.validateRequiredFields(this.returnMapRequiredFields(expense));

        if (Objects.nonNull(expense.getCustomerId()))
            expense.setCustomer(Customers.builder().id(expense.getCustomerId()).build());
        expense.setCategory(Categories.builder().id(categoryID).build());
        expense.setUserLastchange("API_CREATE_EXPENSE");

        Expenses savedExpense = expensesRepository.save(expense);

        return Expenses.builder()
                .id(savedExpense.getId())
                .build();
    }

    public void updateExpense(Long id, Expenses expense) {

        this.validateRequiredFields(this.returnMapRequiredFields(expense));

        Expenses expenseBD = expensesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Expense not found with -> ID: " + id));

        if (Objects.nonNull(expense.getCustomerId()))
            expense.setCustomer(Customers.builder().id(expense.getCustomerId()).build());
        expense.setId(id);
        expense.setVersion(expenseBD.getVersion());
        expense.setUserLastchange("API_UPDATE_EXPENSE");

        expensesRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expensesRepository.deleteById(id);
    }

    private Map<String, Optional<?>> returnMapRequiredFields(Expenses object) {
        return Map.of(
                "Amount", Optional.ofNullable(object).map(Expenses::getAmount),
                "Description", Optional.ofNullable(object).map(Expenses::getDescription),
                "Accrual Date", Optional.ofNullable(object).map(Expenses::getAccrualDate),
                "Transaction Date", Optional.ofNullable(object).map(Expenses::getTransactionDate)
                );
    }
}
