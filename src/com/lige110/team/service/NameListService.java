package com.lige110.team.service;

import com.lige110.team.domain.*;

import static com.lige110.team.service.Data.*;

public class NameListService {
    private Employee[] employees;

    public NameListService() {
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < employees.length; i++) {

            int type = Integer.parseInt(EMPLOYEES[i][0]);
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);

            //
            Equipment eq;
            double bonus;
            int stock;

            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    eq = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, eq);
                    break;
                case DESIGNER:
                    eq = createEquipment(i);
                    bonus = Integer.parseInt(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, eq, bonus);
                    break;
                case ARCHITECT:
                    eq = createEquipment(i);
                    bonus = Integer.parseInt(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, eq, bonus,
                            stock);
                    break;
            }
        }
    }

    private Equipment createEquipment(int index) {
        int type = Integer.parseInt(EQUIPMENTS[index][0]);
        switch (type){
            case PC:
                return new PC(EQUIPMENTS[index][1],EQUIPMENTS[index][2]);
            case NOTEBOOK:
                int price = Integer.parseInt(EQUIPMENTS[index][2]);
                return new NoteBook(EQUIPMENTS[index][1],price);
            case PRINTER:
                return new Printer(EQUIPMENTS[index][1],EQUIPMENTS[index][2]);
        }

        return null;
    }

    public Employee[] getAllEmployees(){return employees;}

    public Employee getEmployee(int id) throws TeamException{
        for (Employee e : employees){
            if(e.getId() == id)
                return e;
        }
        throw new TeamException("This employee doesn't exist! ");
    }
}