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
        for (EmployeePayrollData copy: employeePayrollData)
            System.out.println(copy);
        Assert.assertEquals(3, employeePayrollData.size());
    }
}

