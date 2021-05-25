package ui;

import app.DrivingSchool;
import app.UICodeSaver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
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
        UICodeSaver cs = new UICodeSaver();
        int gender;
        int repeat = 1;
        int index = 1;

        while (repeat == 1) {
            System.out.println(index + ". cyklus programu");
            System.out.println("");
            System.out.println(cs.start());
            System.out.println("");
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
                gender = 1;
                System.out.println(cs.menu());
                
                System.out.println("");                

                //ds.saveResults("vysledky.txt");                
                int n = sc.nextInt();

                while (n < 0 || n > 4) {
                    System.out.println("Zadané číslo musí být 0, 1, 2, 3, 4");
                    n = sc.nextInt();
                }

                System.out.println(cs.usersChoice(n, ds, gender));
                System.out.println("");
                System.out.println(cs.filtr());
                //přidat filtrování, přidat data narození, smazat data zkoušek
                //přidat možnosti ukládání
                //optimalizace
//                                                      String DEST = "results/pdfko.pdf";
//                                                      File file = new File(DEST);
//                                                      file.getParentFile().mkdirs();
//                                                      ds.savePDF(DEST);
            } catch (IOException e) {
                System.out.println("Nastal problém" + e.getMessage());
            }
            System.out.println("Zadejte 1 pro opakování programu, 0 pro konec");
            repeat = 3;
            index++;
            while (repeat < 0 || repeat > 1) {
                System.out.println("Zadané číslo musí být 1 pro opakování, nebo 0 pro konec");
                repeat = sc.nextInt();
            }
        }
    }
}
