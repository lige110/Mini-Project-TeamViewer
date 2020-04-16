package com.lige110.team.junit;


import com.lige110.team.domain.Employee;
import com.lige110.team.service.NameListService;
import com.lige110.team.service.TeamException;
import org.junit.Test;

public class NameListServiceTest {


    @Test
    public void testGetAllEmployees(){
        NameListService listService = new NameListService();

        Employee[] employees = listService.getAllEmployees();

        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee()  {
        int id = 2;
        NameListService nameService = new NameListService();

        try {
            Employee e = nameService.getEmployee(id);
            System.out.println(e);
        } catch (TeamException teamException) {
            System.out.println(teamException.getMessage());
        }

    }
}
