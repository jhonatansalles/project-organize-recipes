package br.com.vibbra.organizerecipes.repository.expenses;

import br.com.vibbra.organizerecipes.model.entity.expenses.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}