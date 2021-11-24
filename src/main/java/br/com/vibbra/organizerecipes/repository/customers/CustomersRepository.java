package br.com.vibbra.organizerecipes.repository.customers;

import br.com.vibbra.organizerecipes.model.entity.customers.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

    @Transactional(readOnly = true)
    Boolean existsByCnpj(String cnpj);

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Customers c WHERE (c.commercialName LIKE '%:NAME%' OR c.legalName like '%:NAME%') AND c.cnpj = :CNPJ ")
    List<Customers> findAllByNameAndCnpj(@Param("NAME") String name, @Param("CNPJ") String cnpj);
}