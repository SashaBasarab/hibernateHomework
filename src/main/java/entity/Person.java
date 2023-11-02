package entity;

import javax.persistence.*;


public abstract class Person {

    private String name;

    private int age;

    private int energy = 100;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayName(){
        System.out.println("My name is " + this.name);
    }

    public void add(int num1, int num2){
        System.out.println("Sum = " + (num1 + num2));
    }

    public void communicationWithTeam(){
        System.out.println("Hey, could you gank?");
    }

    public abstract void doWork();

    public abstract boolean registrationForMatch();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

}


