package com.bl.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayrollTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayroll();
        for (EmployeePayrollData copy : employeePayrollData)
            System.out.println(copy);
        Assert.assertEquals(3, employeePayrollData.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayRollService.readEmployeePayroll();
        for (EmployeePayrollData data : employeePayrollData)
            System.out.println(data);
        employeePayRollService.updateEmployeeSalary("Terisa", 3000000.00);
        boolean result = employeePayRollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assert.assertTrue(result);
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        EmployeePayrollDBService employeePayRollDBService = new EmployeePayrollDBService();
        employeePayRollService.readEmployeePayroll();
        LocalDate startDate = LocalDate.of(2019, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> employeePayRollDBData = employeePayRollDBService.readEmployeePayRollForDateRange(startDate, endDate);
        for (EmployeePayrollData data : employeePayRollDBData)
            System.out.println(data);
        Assert.assertEquals(2, employeePayRollDBData.size());
    }

    @Test
    public void givenPayrollData_WhenAverageSalaryRetrieveByGender_ShouldReturnProperValue() {
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        employeePayRollService.readEmployeePayroll();
        Map<String, Double> averageSalaryByGender = employeePayRollService.readAverageSalaryByGender();
        Assert.assertTrue(averageSalaryByGender.get("M").equals(2000000.00) && averageSalaryByGender.get("F").equals(3500000.00));
    }

    @Test
    public void givenPayrollData_WhenCountByGender_ShouldReturnCount() {
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        employeePayRollService.readEmployeePayroll();
        Map<String, Integer> countByGender = employeePayRollService.readCountSalaryByGender();
        Assert.assertTrue(countByGender.get("M").equals(2) && countByGender.get("F").equals(2));
    }
}

