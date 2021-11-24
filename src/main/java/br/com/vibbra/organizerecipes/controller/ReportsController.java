package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.request.ReportRevenueRequest;
import br.com.vibbra.organizerecipes.model.response.reports.ReportRevenueCustomerResponse;
import br.com.vibbra.organizerecipes.model.response.reports.ReportRevenueMonthResponse;
import br.com.vibbra.organizerecipes.model.response.reports.ReportTotalRevenueResponse;
import br.com.vibbra.organizerecipes.service.reports.ReportsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reports")
@PreAuthorize("hasRole('USER')")
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @ApiOperation(value = "Total Revenue Report.")
    @PostMapping(path = "/total-revenue", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportTotalRevenueResponse> totalRevenue(@RequestBody ReportRevenueRequest revenueRequest) {
        return ResponseEntity.ok(reportsService.getTotalRevenue(revenueRequest));
    }

    @ApiOperation(value = "Revenue by Month Report.")
    @PostMapping(path = "/revenue-by-month", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportRevenueMonthResponse> revenueByMonth(@RequestBody ReportRevenueRequest revenueRequest) {
        return ResponseEntity.ok(reportsService.getRevenueByMonth(revenueRequest));
    }

    @ApiOperation(value = "Revenue by Customer Report.")
    @PostMapping(path = "/revenue-by-customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportRevenueCustomerResponse> revenueByCustomer(@RequestBody ReportRevenueRequest revenueRequest) {
        return ResponseEntity.ok(reportsService.getRevenueByCustomer(revenueRequest));
    }
}