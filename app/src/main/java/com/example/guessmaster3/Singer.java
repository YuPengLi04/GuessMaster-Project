package com.example.guessmaster3;

public class Singer extends Person implements Cloneable {
    private String debutAlbum;
    private Date debutAlbumReleaseDate;

    // Constructor
    public Singer(String name, Date born, String gender, String debutAlbum, Date debutAlbumReleaseDate, double difficulty) {
        super(name, born, gender, difficulty);
        this.debutAlbum = debutAlbum;
        this.debutAlbumReleaseDate = new Date(debutAlbumReleaseDate);
    }

    // Copy constructor
    public Singer(Singer singer) {
        super(singer);
        this.debutAlbum = singer.debutAlbum;
        this.debutAlbumReleaseDate = new Date(singer.debutAlbumReleaseDate);
    }

    // Getter method
    public String getDebutAlbum() {
        return (debutAlbum);
    }

    // Getter method and also preventing privacy leak by making a new copy
    public Date getDebutAlbumReleaseDate() {
        return (new Date(debutAlbumReleaseDate));
    }

    // For returning which type of entity it is
    @Override
    public String entityType() {
        return ("Singer!");
    }

    // Preventing Privacy leak by overriding the method
    @Override
    public Singer clone() {
        return (new Singer(this.getName(),this.getBorn(),this.getGender(), this.debutAlbum, this.debutAlbumReleaseDate,this.getDifficulty()));
    }

    // Overriding the toString function to print the general case from entity and the specific case
    @Override
    public String toString() {
        return (super.toString()+"\nDebut Album: " + debutAlbum + "\nRelease Date: " + debutAlbumReleaseDate);
    }
}
