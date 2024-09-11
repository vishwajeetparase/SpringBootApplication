package com.artist.dto;

// public record ArtistRequest(String firstName, String lastName) {
// } Java 14+ feature(record)

public class ArtistRequest {
    private String firstName;
    private String lastName;

    // Constructor
    public ArtistRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Optional: Override toString, equals, hashCode if needed
}

