package br.com.vibbra.organizerecipes.repository.revenues;

import br.com.vibbra.organizerecipes.model.entity.revenues.Revenues;
import br.com.vibbra.organizerecipes.model.response.reports.ReportTotalRevenueResponse;
import br.com.vibbra.organizerecipes.model.response.reports.RevenueCustomerItem;
import br.com.vibbra.organizerecipes.model.response.reports.RevenueMonthItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RevenuesRepository extends JpaRepository<Revenues, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT new br.com.vibbra.organizerecipes.model.response.reports.ReportTotalRevenueResponse(SUM(r.amount), MAX(r.amount)) " +
            "FROM Revenues r " +
            "WHERE YEAR(r.transactionDate) = :FISCALYEAR ")
    ReportTotalRevenueResponse findTotalByFiscalYear(@Param("FISCALYEAR") Integer fiscalYear);

    @Transactional(readOnly = true)
    @Query("SELECT new br.com.vibbra.organizerecipes.model.response.reports.RevenueMonthItem( " +
            "CASE WHEN month(r.transactionDate) = 1 THEN 'January' when month(r.transactionDate) = 2 THEN 'February'" +
            "WHEN month(r.transactionDate) = 3 THEN 'March' WHEN month(r.transactionDate) = 4 THEN 'April'" +
            "WHEN month(r.transactionDate) = 5 THEN 'May' WHEN month(r.transactionDate) = 6 THEN 'June'" +
            "WHEN month(r.transactionDate) = 7 THEN 'July' WHEN month(r.transactionDate) = 8 THEN 'August'" +
            "WHEN month(r.transactionDate) = 9 THEN 'September' WHEN month(r.transactionDate) = 10 THEN 'October'" +
            "WHEN month(r.transactionDate) = 11 THEN 'November' WHEN month(r.transactionDate) = 12 THEN 'December'" +
            "END AS monthName, r.amount) " +
            "FROM Revenues r " +
            "WHERE year(r.transactionDate) = :FISCALYEAR ")
    List<RevenueMonthItem> findRevenuesMonthByFiscalYear(@Param("FISCALYEAR") Integer fiscalYear);

    @Transactional(readOnly = true)
    @Query("SELECT new br.com.vibbra.organizerecipes.model.response.reports.RevenueCustomerItem(c.commercialName, r.amount) " +
            "FROM Revenues r " +
            "JOIN r.customer c " +
            "WHERE year(r.transactionDate) = :FISCALYEAR ")
    List<RevenueCustomerItem> findRevenuesCustomerByFiscalYear(@Param("FISCALYEAR") Integer fiscalYear);
}