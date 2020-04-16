package com.lige110.team.domain;

public class Designer extends Programmer{
    private double bonus;

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary,
                    Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getDetailsForTeam() {
        return getMemberDetails() + "\tDesigner\t" + getBonus();
    }

    @Override
    public String toString() {
        return getDetails() + "\tDesigner\t" + getStatus() + "\t" +
                getBonus() +"\t\t\t" + getEquipment().getDescription();
    }
}
