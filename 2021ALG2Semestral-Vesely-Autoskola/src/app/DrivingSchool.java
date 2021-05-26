package app;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
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
import java.util.Comparator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MartinVesely
 */
public class DrivingSchool {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<Driver> drivers;
    private List<Driver> passedTests;
    private List<Driver> didntPassedTheory;
    private List<Driver> didntPassedDriving;

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
                if (d.getTestPoints() < 45) {
                    didntPassedTheory.add(d);
                }

            }
        }
    }

    public String printAllDrivers(int n, int compare) {
        if (compare == 1) {
            sortPoints sp = new sortPoints();
            Collections.sort(drivers, sp);
        }
        if (compare == 2) {
            sortPointsUp sp = new sortPointsUp();
            Collections.sort(drivers, sp);
        }
        if (compare == 3) {
            Collections.sort(drivers);
        }
        if (compare == 4) {
            Comparator<Driver> mapComparator = (Driver a, Driver b) -> a.getBirthDate().compareTo(b.getBirthDate());
            Collections.sort(didntPassedTheory, mapComparator);
        }
        StringBuilder sb = new StringBuilder();
        for (Driver d : drivers) {
            switch (n) {
                case 1:
                    sb.append(d).append("\n");
                    break;
                case 2:
                    if (d.getGender() == 'F') {
                        sb.append(d).append("\n");
                    }
                    break;
                case 3:
                    if (d.getGender() == 'M') {
                        sb.append(d).append("\n");
                    }
                    break;
                default:
                    return "něco je špatně";
            }
        }
        return sb.toString();
    }

    public String printPassedDrivers(int n, int compare) {
        if (compare == 1) {
            sortPoints sp = new sortPoints();
            Collections.sort(passedTests, sp);
        }
        if (compare == 2) {
            sortPointsUp sp = new sortPointsUp();
            Collections.sort(passedTests, sp);
        }
        if (compare == 3) {
            Collections.sort(passedTests);
        }
        if (compare == 4) {
            Comparator<Driver> mapComparator = (Driver a, Driver b) -> a.getBirthDate().compareTo(b.getBirthDate());
            Collections.sort(didntPassedTheory, mapComparator);
        }
        StringBuilder sb = new StringBuilder();
        for (Driver d : passedTests) {
            switch (n) {
                case 1:
                    sb.append(d).append("\n");
                    break;
                case 2:
                    if (d.getGender() == 'F') {
                        sb.append(d).append("\n");
                    } else {
                        break;
                    }
                    break;
                case 3:
                    if (d.getGender() == 'M') {
                        sb.append(d).append("\n");
                    }
                    break;
                default:
                    return "něco je špatně";
            }
        }
        return sb.toString();
    }

    public String printHeader() {
        return String.format("%10s %10s %7s %10s %15s", "Jméno", "Přijímení", "Pohlaví", "Počet bodů", "Datum narození");
    }

    public void saveResults(String filename, int choice) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))))) {
            switch (choice) {
                case 1:
                    for (Driver driver : drivers) {
                        pw.println(driver.toString());
                    }
                    break;
                case 2:
                    for (Driver driver : passedTests) {
                        pw.println(driver.toString());
                    }
                    break;
                case 3:
                    for (Driver driver : didntPassedTheory) {
                        pw.println(driver.toString());
                    }
                    break;
                case 4:
                    for (Driver driver : didntPassedDriving) {
                        pw.println(driver.toString());
                    }
                    break;
                default:
                    System.out.println("Obraťte se na podporu, prosím.");
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
                        if (r.getId() == id) {
                            r.setDriveDate(driveDate);
                            passedTests.add(r);
                        }
                    }
                } else {
                    for (Driver r : drivers) {
                        if (r.getId() == id) {
                            didntPassedDriving.add(r);
                        }
                    }

                }
            }
        }
    }

    public String printDidintPassedTheory(int n, int compare) {
        if (compare == 1) {
            sortPoints sp = new sortPoints();
            Collections.sort(didntPassedTheory, sp);
        }
        if (compare == 2) {
            sortPointsUp sp = new sortPointsUp();
            Collections.sort(didntPassedTheory, sp);
        }
        if (compare == 3) {
            Collections.sort(didntPassedTheory);
        }
        if (compare == 4) {
            Comparator<Driver> mapComparator = (Driver a, Driver b) -> a.getBirthDate().compareTo(b.getBirthDate());
            Collections.sort(didntPassedTheory, mapComparator);
        }
        StringBuilder sb = new StringBuilder();
        for (Driver d : didntPassedTheory) {
            switch (n) {
                case 1:
                    sb.append(d).append("\n");
                    break;
                case 2:
                    if (d.getGender() == 'F') {
                        sb.append(d).append("\n");   //tady je problém, stejně tak case 3 
                        break;
                    }
                    break;
                case 3:
                    if (d.getGender() == 'M') {
                        sb.append(d).append("\n");
                        break;
                    }
                    break;
                default:
                    return "něco je špatně";
            }
        }
        return sb.toString();
    }

    public String printDidntPassedDriving(int n, int compare) {
        if (compare == 1) {
            sortPoints sp = new sortPoints();
            Collections.sort(didntPassedDriving, sp);
        }
        if (compare == 2) {
            sortPointsUp sp = new sortPointsUp();
            Collections.sort(didntPassedDriving, sp);
        }
        if (compare == 3) {
            Collections.sort(didntPassedDriving);
        }
        if (compare == 4) {
            Comparator<Driver> mapComparator = (Driver a, Driver b) -> a.getBirthDate().compareTo(b.getBirthDate());
            Collections.sort(didntPassedTheory, mapComparator);
        }
        StringBuilder sb = new StringBuilder();
        for (Driver d : didntPassedDriving) {
            switch (n) {
                case 1:
                    sb.append(d).append("\n");
                    break;
                case 2:
                    if (d.getGender() == 'F') {
                        sb.append(d).append("\n");
                    }
                    break;
                case 3:
                    if (d.getGender() == 'M') {
                        sb.append(d).append("\n");
                    }
                    break;
                default:
                    return "něco je špatně";
            }

        }
        return sb.toString();
    }
//    public void savePDF(String filename) throws FileNotFoundException {
//        PdfWriter writer = new PdfWriter(filename);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document document = new Document(pdf);
//        document.add(new Paragraph("Hello World!"));
//        document.close();
////            StringBuilder sb = new StringBuilder();
////            for (Driver d : didntPassedTheory) {
////                sb.append(d).append("\n");
////            }
////            Paragraph kokot = new Paragraph(sb.toString());
////            document.add(kokot);
//        
//        System.out.println("List added");
//    }
}
