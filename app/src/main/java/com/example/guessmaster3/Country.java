package com.example.guessmaster3;

public class Country extends Entity implements Cloneable{
    private String capital;

    // Constructor
    public Country(String name, Date born, String capital, double difficulty) {
        super(name, born, difficulty);
        this.capital = capital;
    }

    // Copy constructor
    public Country(Country other) {
        super(other);
        this.capital = other.capital;
    }

    // Getter method
    public String getCapital() {
        return (capital);
    }

    // For returning which type of entity it is
    @Override
    public String entityType() {

        return ("Country!");
    }

    // Preventing Privacy leak by overriding the method
    @Override
    public Country clone() {
        return (new Country(getName(), getBorn(), capital, getDifficulty()));
    }

    // Overriding the toString function to print the general case from entity and the specific case
    @Override
    public String toString() {
        return (super.toString() + "\nCapital: " + capital);
    }
}
