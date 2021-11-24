package br.com.vibbra.organizerecipes.service.reports;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.model.request.ReportRevenueRequest;
import br.com.vibbra.organizerecipes.model.response.reports.*;
import br.com.vibbra.organizerecipes.repository.revenues.RevenuesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportsService extends BaseService {

    @Autowired
    RevenuesRepository revenueRepository;

    public ReportTotalRevenueResponse getTotalRevenue(ReportRevenueRequest revenueRequest) {
        return revenueRepository.findTotalByFiscalYear(revenueRequest.getFiscalYear());
    }

    public ReportRevenueMonthResponse getRevenueByMonth(ReportRevenueRequest revenueRequest) {
        List<RevenueMonthItem> revenuesMonthList = revenueRepository.findRevenuesMonthByFiscalYear(revenueRequest.getFiscalYear());

        List<RevenueMonthItem> revenuesMonthGroups = revenuesMonthList.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getMonthName(),
                        Collectors.summingDouble(item -> item.getMonthRevenue().doubleValue())))
                .entrySet()
                .stream()
                .map(item -> new RevenueMonthItem(item.getKey(), new BigDecimal(item.getValue())))
                .collect(Collectors.toList());

        BigDecimal maxRevenueAmount = revenuesMonthList.stream()
                .map(RevenueMonthItem::getMonthRevenue)
                .max(BigDecimal::compareTo)
                .orElse(null);

        return ReportRevenueMonthResponse.builder()
                .revenue(revenuesMonthGroups)
                .maxRevenueAmount(maxRevenueAmount)
                .build();
    }

    public ReportRevenueCustomerResponse getRevenueByCustomer(ReportRevenueRequest revenueRequest) {
        List<RevenueCustomerItem> revenuesCusomerList = revenueRepository.findRevenuesCustomerByFiscalYear(revenueRequest.getFiscalYear());

        List<RevenueCustomerItem> revenuesCustomerGroups = revenuesCusomerList.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getCustomerName(),
                        Collectors.summingDouble(item -> item.getRevenue().doubleValue())))
                .entrySet()
                .stream()
                .map(item -> new RevenueCustomerItem(item.getKey(), new BigDecimal(item.getValue())))
                .collect(Collectors.toList());

        BigDecimal maxRevenueAmount = revenuesCusomerList.stream()
                .map(RevenueCustomerItem::getRevenue)
                .max(BigDecimal::compareTo)
                .orElse(null);

        return ReportRevenueCustomerResponse.builder()
                .revenue(revenuesCustomerGroups)
                .maxRevenueAmount(maxRevenueAmount)
                .build();
    }
}
