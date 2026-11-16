// student number: 20347665
// name: Yu Peng Li

package com.example.guessmaster3;

abstract class Entity {
    private String name;
    private Date born;
    private double difficulty;

    // Added the difficulty to the constructor
    public Entity(String name, Date born, double difficulty) {
        this.name = name;
        this.born = new Date(born); // Copy constructor of Date is assumed to be present.
        this.setDifficulty(difficulty);
    }

    // Added the difficulty to the copy constructor
    public Entity(Entity other) {
        this.name = other.name;
        this.born = new Date(other.born); // Prevent privacy leak.
        this.difficulty = other.difficulty;
    }

    public String getName() {
        return name;
    }

    public Date getBorn() {
        return new Date(born); // Return a copy to prevent privacy leak.
    }

    // returning the value of private variable called difficulty
    public double getDifficulty() {
        return difficulty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorn(Date born) {
        this.born = new Date(born); // Use copy constructor.
    }

    // Checking if the difficulty is within 0 to 1
    public void setDifficulty(double difficulty) {
        if (difficulty < 0.0 || difficulty > 1.0) {
            System.out.println("Invalid difficulty");
        } else {
            this.difficulty = difficulty;
        }
    }

    // Calculating the ticket number by multiplying the difficulty by 100
    public int getAwardedTicketNumber(double difficulty) {
        return (int)(difficulty*100);
    }

    public String toString() {
        return ("Name: " + name + "\nBorn At: " + born); // Date's toString() method is assumed to handle date formatting.
    }

    public boolean equals(Entity entity) {
        System.out.print(this.name.equals(entity.name) && this.born.equals(entity.born));
        return (this.name.equals(entity.name) && this.born.equals(entity.born));
    }

    public abstract String entityType();
    public abstract Entity clone();

    public String welcomeMessage() {
        return ("**********************************\nWelcome! Let’s start the game! This entity is a " + entityType() +"\n");
    }

    public String closingMessage() {
        return ("Congratudations! The detailed information of the entity you guessed is:\n" + toString());
    }

}