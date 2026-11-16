package com.example.guessmaster3;

public class Politician extends Person implements Cloneable{
    private String party;

    // Constructor
    public Politician(String name, Date born, String gender, String party, double difficulty) {
        super(name, born, gender, difficulty);
        this.party = party;
    }

    // Copy constructor
    public Politician(Politician politician) {
        super(politician);
        this.party = politician.party;
    }

    public String getParty() {
        return (party);
    }

    // For returning which type of entity it is
    @Override
    public String entityType() {

        return ("Politician!");
    }

    // Preventing Privacy leak by overriding the method
    @Override
    public Politician clone() {
        return (new Politician(this.getName(),this.getBorn(),this.getGender(), this.party ,this.getDifficulty()));
    }

    // Overriding the toString function to print the general case from entity and the specific case
    @Override
    public String toString() {
        return (super.toString()+"\nParty: " + party);
    }
}
