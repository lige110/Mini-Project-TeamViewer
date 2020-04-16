package com.lige110.team.service;

import com.lige110.team.domain.*;

public class TeamService {
    private static int counter =1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total =0;

    public TeamService(){
    }

    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];

        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException {
        if (total >= MAX_MEMBER)
            throw new TeamException("The team is full, cannot add more people.");
        if(!(e instanceof Programmer))
            throw new TeamException("This person is not developer, cannot be added.");

        Programmer p = (Programmer)e;

        if(isExist(p))
            throw new TeamException("This employee is already in team.");

        if(p.getStatus().getNAME().equals("BUSY")){
            throw new TeamException("This employee is already working for other team.");
        }else if (p.getStatus().getNAME().equals("VOCATION")){
            throw new TeamException("This Employee is taking a vacation");
        }

        int numOfArch = 0, numOfDsgn = 0, numOfPrg = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) numOfArch++;
            else if (team[i] instanceof Designer) numOfDsgn++;
            else if (team[i] instanceof Programmer) numOfPrg++;
        }

        if (p instanceof Architect) {
            if (numOfArch >= 1) throw new TeamException("There should be only one Architect in team");
        } else if (p instanceof Designer) {
            if (numOfDsgn >= 2) throw new TeamException("There are at most two designers in team");
        } else if (p instanceof Programmer) {
            if (numOfPrg >= 3) throw new TeamException("There are at most three Programmers in team");
        }

        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;
    }

    private boolean isExist(Programmer p) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == p.getId()) return true;
        }

        return false;
    }

    public void removeMember(int memberId) throws TeamException{
        int n =0;

        for (; n < total; n++) {
            if (team[n].getMemberId() == memberId) {
                team[n].setStatus(Status.FREE);
                break;
            }
        }
        // if not find throw Exception
        if (n == total)
            throw new TeamException("cannot find this member, delete fail");
        //后面的元素覆盖前面的元素
        for (int i = n + 1; i < total; i++) {
            team[i - 1] = team[i];
        }
        team[--total] = null;
    }


}
