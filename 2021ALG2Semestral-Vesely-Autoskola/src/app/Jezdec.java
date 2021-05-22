package app;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marti_000
 */
public class Jezdec {
    private String firstName;
    private String secondName;
    private int testPoints;
    private char gender;
    private LocalDate testDate;

    public Jezdec(String firstName, String secondName, int testPoints, char gender, LocalDate testDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.testPoints = testPoints;
        this.gender = gender;
        this.testDate = testDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getTestPoints() {
        return testPoints;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    @Override
    public String toString() {
        return String.format("%10s %10s %d %s %s", firstName,secondName,testPoints,gender,testDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
    
    
    
    
}
