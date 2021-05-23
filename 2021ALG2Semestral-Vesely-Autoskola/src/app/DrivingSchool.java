package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marti_000
 */
public class DrivingSchool {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<Driver> drivers;
    private List<Driver> passedTests;
    private List<Driver> didntPassedTheory;
    private List<Driver> didntPassedDriving;
    
    private int testMaxPoints;
    private int testPassPoints;

    public DrivingSchool() {
        drivers = new ArrayList<>();
        passedTests = new ArrayList<>();
        didntPassedTheory = new ArrayList<>();
        didntPassedDriving = new ArrayList<>();
    }

    public void loadResults(String filename) throws FileNotFoundException, IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            String[] parts;
            String firstName;
            String secondName;
            int testPoints;
            LocalDate birth;
            String[] firstLastName;
            char gender;
            int id;
            Driver d;
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split(","); //jmeno prijimeni,pocet bodu,datum(yyyy-mm-dd),pohlavi
                firstLastName = parts[0].split(" ");
                firstName = firstLastName[0];
                secondName = firstLastName[1];
                testPoints = Integer.parseInt(parts[1]);
                birth = LocalDate.parse(parts[2], dtf);
                gender = parts[3].charAt(0);
                id = Integer.parseInt(parts[4]);
                d = new Driver(firstName, secondName, testPoints, gender, birth, id);

                drivers.add(d);
            }
        }
    }

    public String printAllDrivers() {
        Collections.sort(drivers);
        StringBuilder sb = new StringBuilder();
        for (Driver d : drivers) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }
    
    public String printPassedDrivers() {
        Collections.sort(passedTests);
        StringBuilder sb = new StringBuilder();
        for (Driver d : passedTests) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }
    
    public String printHeader(){
        return String.format("%10s %10s %7s %10s", "Jméno", "Přijímení", "Pohlaví", "Počet bodů");
    }
    
    public void saveResults(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))))) {
            for (Driver driver : drivers) {
                pw.println(driver.toString());
            }
        }
    }

    public void loadTestPoints(String filename) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;
            String[] parts;
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split(","); //jmeno prijimeni,pocet bodu,datum(yyyy-mm-dd),pohlavi
                this.testPassPoints = Integer.parseInt(parts[0]);
                this.testMaxPoints = Integer.parseInt(parts[1]);
            }
        }
    }

    public void loadDrivingTests(String filename) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            boolean driveTest;
            LocalDate driveDate;
            int id;
            String line;
            String[] parts;
            br.readLine();
            Driver d;
            while ((line = br.readLine()) != null) {
                parts = line.split(","); //jmeno prijimeni,pocet bodu,datum(yyyy-mm-dd),pohlavi
                id = Integer.parseInt(parts[0]);
                driveTest = Boolean.parseBoolean(parts[1]);
                driveDate = LocalDate.parse(parts[2], dtf);
                if (driveTest == true) {
                    for (Driver r : drivers) {
                        if (r.getId() == id){
                            r.setDriveDate(driveDate);
                            passedTests.add(r);
                        }
                    }
                } else {
                    for (Driver r : drivers) {
                        if (r.getId() == id){
                            didntPassedDriving.add(r);
                        }
                    }
                }
            }
        }
    }
    
    public String printDidintPassedTheory() {
        for (Driver d : drivers) {
            if(d.getTestPoints() < 45){
                didntPassedTheory.add(d);
            }                
        }       
        Collections.sort(didntPassedTheory);
        StringBuilder sb = new StringBuilder();
        for (Driver d : didntPassedTheory) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }
    
    public String printDidntPassedDriving() {               
        Collections.sort(didntPassedDriving);
        StringBuilder sb = new StringBuilder();
        for (Driver d : didntPassedDriving) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }
}
