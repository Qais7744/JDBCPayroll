package com.bl.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class EmployeePayrollTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException {
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
        employeePayRollService.updateEmployeeSalary("Charliy", 4000000.00);
        boolean result = employeePayRollService.checkEmployeePayrollInSyncWithDB("Charliy");
        Assert.assertTrue(result);
    }
}

