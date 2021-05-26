package app;


import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MartinVesely
 */
public class Driver implements Comparable<Driver>{
    //hodnoty pro konstruktor
    private String firstName;
    private String secondName;
    private int testPoints;
    private char gender;
    private LocalDate birthDate;
    private int id;
    
    //hodnoty pro settre
    private LocalDate driveDate;

    public Driver(String firstName, String secondName, int testPoints, char gender, LocalDate testDate, int id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.testPoints = testPoints;
        this.gender = gender;
        this.birthDate = testDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getId() {
        return id;
    }        

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }     

    @Override
    public String toString() {        
        return  String.format("%10s %10s %4s %9d %17s", firstName,secondName,gender,testPoints, birthDate.format(DateTimeFormatter.ISO_DATE));        
    }
    
    @Override
    public int compareTo(Driver o) {
        Collator col = Collator.getInstance(new Locale("cs", "CZ"));
        return col.compare(this.firstName,o.firstName);
    }
        
}

class sortPoints implements Comparator<Driver>{
    @Override
    public int compare(Driver a, Driver b){
        return Integer.compare(b.getTestPoints(), a.getTestPoints());
    }
}

class sortPointsUp implements Comparator<Driver>{
    @Override
    public int compare(Driver a, Driver b){
        return Integer.compare(a.getTestPoints(), b.getTestPoints());
    }
}

