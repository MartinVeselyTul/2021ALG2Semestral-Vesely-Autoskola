package ui;

import app.DrivingSchool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marti_000
 */
public class DrivingSchoolUI {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DrivingSchool ds = new DrivingSchool();
        try {
            while (true) {
                try {
                    System.out.println("Zadejte název souboru s teoretickými testy");
                    //ds.loadResults("data/" + sc.next());
                    ds.loadResults("data/autoskola_testy.csv");
                    //System.out.println(ds.printAllDrivers());
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Zadaný soubor neexistuje.");
                }
            }
            while (true) {
                try {
                    System.out.println("Zadejte název souboru s praktickými testy");
                    //ds.loadDrivingTests("data/" + sc.next());
                    ds.loadDrivingTests("data/driveTest.csv");
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Zadaný soubor neexistuje.");
                }
            }
            //ds.saveResults("vysledky.txt");
            System.out.println("Účastníci autoškoly");
            System.out.println(ds.printHeader());
            System.out.println(ds.printAllDrivers());
            
            System.out.println("Udělali autoškolu");
            System.out.println(ds.printHeader());
            System.out.println(ds.printPassedDrivers());
            
            System.out.println("Neudělali testy");
            System.out.println(ds.printHeader());
            System.out.println(ds.printDidintPassedTheory());
            
            System.out.println("Neudělali závěrečné jízdy");
            System.out.println(ds.printHeader());
            System.out.println(ds.printDidntPassedDriving());
        } catch (IOException e) {
            System.out.println("Nastal problém" + e.getMessage());
        }
    }
}
