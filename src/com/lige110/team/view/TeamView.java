package com.lige110.team.view;
import com.lige110.team.domain.*;
import com.lige110.team.service.*;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu() {
        boolean loopFlag = true;
        char key = 0;

        do {
            if (key != '1') {
                listAllEmployees();
            }
            System.out.print("1-DeveloperTeam 2-AddDeveloperTeam  3-DeleteTeamMember 4-Quit   choose from(1-4)：");
            key = TSUtility.readMenuSelection();
            System.out.println();
            switch (key) {
                case '1':
                    listTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("Confirm Quit?(Y/N)：");
                    char yn = TSUtility.readConfirmSelection();
                    if (yn == 'Y')
                        loopFlag = false;
                    break;
            }
        } while (loopFlag);
    }

    // display all employees
    private void listAllEmployees() {
        System.out
                .println("\n-------------------------------DeveloperTEAMManagement--------------------------------\n");
        Employee[] emps = listSvc.getAllEmployees();
        if (emps.length == 0) {
            System.out.println("No Records！");
        } else {
            System.out.println("ID\tName\t\tAge\tSalary\tJob\t\t\tStatus\tBonus\tStock\tEquipment");
        }

        for (Employee e : emps) {
            System.out.println(" " + e);
        }
        System.out
                .println("-------------------------------------------------------------------------------");
    }

    // display developer group members
    private void listTeam() {
        System.out
                .println("\n--------------------DeveloperTeamMember---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if (team.length == 0) {
            System.out.println("Developer Team is empty！");
        } else {
            System.out.println("TID/ID\tName\tAge\tSalary\tJob\tBonus\tStock");
        }

        for (Programmer p : team) {
            System.out.println(" " + p.getDetailsForTeam());
        }
        System.out
                .println("-----------------------------------------------------");
    }

    // add employee to developer team
    private void addMember() {
        System.out.println("---------------------AddMember---------------------");
        System.out.print("Input ID of Employee to be added. ID：");
        int id = TSUtility.readInt();

        try {
            Employee e = listSvc.getEmployee(id);
            teamSvc.addMember(e);
            System.out.println("Success!");
        } catch (TeamException e) {
            System.out.println("Fail，reason：" + e.getMessage());
        }
        // press enter to continue...
        TSUtility.readReturn();
    }

    // delete member with ID
    private void deleteMember() {
        System.out.println("---------------------DeleteMember---------------------");
        System.out.print("Input ID of Employee to be deleted. TID：");
        int id = TSUtility.readInt();
        System.out.print("Confirm Delete?(Y/N)：");
        char yn = TSUtility.readConfirmSelection();
        if (yn == 'N')
            return;

        try {
            teamSvc.removeMember(id);
            System.out.println("Delete Success");
        } catch (TeamException e) {
            System.out.println("Delete Fail，Reason：" + e.getMessage());
        }
        // press enter to continue...
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }
}
