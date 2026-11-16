package com.example.guessmaster3;

public class Person extends Entity implements Cloneable{
    private String gender;

    // Constructor
    public Person(String name, Date born, String gender, double difficulty) {
        super(name, born, difficulty);
        this.gender = gender;
    }

    // Copy constructor
    public Person(Person person) {
        super(person);
        this.gender = person.gender;
    }

    // Getter method
    public String getGender() {
        return gender;
    }

    // For returning which type of entity it is
    @Override
    public String entityType() {

        return "Person!";
    }

    // Preventing Privacy leak by overriding the method
    @Override
    public Person clone() {
        return new Person(this.getName(),this.getBorn(),this.gender,this.getDifficulty());
    }

    // Overriding the toString function to print the general case from entity and the specific case
    @Override
    public String toString() {
        return super.toString() + "\nGender: " + gender;
    }
}
