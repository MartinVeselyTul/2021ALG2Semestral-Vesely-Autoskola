package app;


import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marti_000
 */
public class Driver implements Comparable<Driver>{
    //hodnoty pro konstruktor
    private String firstName;
    private String secondName;
    private int testPoints;
    private char gender;
    private LocalDate testDate;
    private int id;
    
    //hodnoty pro settre
    private LocalDate driveDate;

    public Driver(String firstName, String secondName, int testPoints, char gender, LocalDate testDate, int id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.testPoints = testPoints;
        this.gender = gender;
        this.testDate = testDate;
        this.id = id;
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

    public int getId() {
        return id;
    }        

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }     

    @Override
    public String toString() {        
        return  String.format("%10s %10s %4s %9d", firstName,secondName,gender,testPoints);        
    }
    
    @Override
    public int compareTo(Driver o) {
        Collator col = Collator.getInstance(new Locale("cs", "CZ"));
        return col.compare(this.secondName,o.secondName);
    }
    
    
    
    
}
