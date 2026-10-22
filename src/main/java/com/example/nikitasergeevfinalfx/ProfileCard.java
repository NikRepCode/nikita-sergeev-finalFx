package com.example.nikitasergeevfinalfx;

public class ProfileCard {
    private final String firstName;
    private final String lastName;
    private final String character;
    private final String maritalStatus;
    private final String photoFilename;

    public ProfileCard(String firstName, String lastName, String character,
                       String maritalStatus, String photoFilename) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.character = character;
        this.maritalStatus = maritalStatus;
        this.photoFilename = photoFilename;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCharacter() {
        return character;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getPhotoFilename() {
        return photoFilename;
    }
}