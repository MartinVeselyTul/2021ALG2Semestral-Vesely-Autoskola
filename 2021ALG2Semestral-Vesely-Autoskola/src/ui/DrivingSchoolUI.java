package ui;

import app.Driver;
import app.DrivingSchool;
import app.UICodeSaver;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/* Upravy
 * Zápis do bin souboru - skoro hotove, nevim, zda se to uklada

 * Zpracovat časy, aby program něco počítal

 * BONUS zkusit přidat funkci, aby šel vložit další jezdec

 * Přidat regulární výraz - u ukládání souborů

 * Napsat David Bálik ohledně pdf ukládání -- po pridani jine iText knihovny ukladani funguje, alespoň na první testovací text, huraa

 * !!!Udělat nový diagram dle videa!!!
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MartinVesely
 */
public class DrivingSchoolUI {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        UICodeSaver cs = new UICodeSaver();
        int gender;
        int repeat = 1;
        int index = 1;
        int saveOrNot;
        int filter;
        int sort;
        int fileSave;

        while (repeat == 1) {
            System.out.println(index + ". cyklus programu");
            System.out.println("");
            System.out.println(cs.start());
            System.out.println("");
            DrivingSchool ds = new DrivingSchool();
            try {
                while (true) {
                    try {
                        System.out.println("Zadejte název souboru s teoretickými testy");
                        //ds.loadResults("data/" + sc.next());
                        ds.loadResults("data/autoskola_testy.csv");
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
                sort = 0;
                System.out.println(cs.menu());

                System.out.println("");

                //ds.saveResults("vysledky.txt");
                try {
                    int tableChoice = sc.nextInt();

                    while (tableChoice < 1 || tableChoice > 4) {
                        System.out.println("Zadané číslo musí být 1, 2, 3, 4");
                        tableChoice = sc.nextInt();
                    }
                    List<Driver> list = new ArrayList<>();
                    ds.changeList(tableChoice, list);
                    
                    System.out.println(cs.usersChoice(tableChoice, ds, gender, sort));
                    System.out.println("");
                    System.out.println(cs.filtr());
                    filter = sc.nextInt();
                    while (filter < 1 || filter > 7) {
                        System.out.println("Zadejte číslo od 1 do 7");
                        filter = sc.nextInt();
                    }

                    System.out.println(cs.filtering(filter, ds, gender, tableChoice));

                    System.out.println("Ukládání souborů.");
                    System.out.println("Pokud chcete soubor uložit, stiskněte 1, pokud ne, stiskněte 0");
                    saveOrNot = sc.nextInt();
                    while (saveOrNot < 0 || saveOrNot > 1) {
                        System.out.println("Stiskněte buď 1, nebo 0");
                        saveOrNot = sc.nextInt();
                    }
                    if (saveOrNot == 1) {
                        System.out.println(cs.savingFormat());

                        System.out.println("V jakém formátu chcete soubor uložit?");
                        fileSave = sc.nextInt();
                        while (fileSave < 1 || fileSave > 4) {
                            System.out.println("Zadejte číslo od 1 do 4");
                            fileSave = sc.nextInt();
                        }
                        while (true) {
                            try {
                                System.out.println("Zadejte název souboru");
                                switch (fileSave) {
                                    case 1:
                                        ds.saveResults(sc.next() + ".txt", tableChoice);
                                        break;
                                    case 2:
                                        ds.saveResults(sc.next() + ".csv", tableChoice);
                                        break;
                                    case 3:
                                        try{
                                        ds.savePDF(sc.next(), tableChoice);
                                        }catch(DocumentException e){
                                            System.out.println("Nastala chyba při vytváření PDF dokumentu" +  e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        ds.saveResultsToBinary(new File(sc.next() + ".dat"), list);
                                }

                                break;
                            } catch (FileNotFoundException e) {
                                System.out.println("Zadaný soubor neexistuje.");
                            }
                        }
                    }
                    System.out.println("Zadejte 1 pro opakování programu, 0 pro konec");
                    repeat = sc.nextInt();
                    index++;
                    while (repeat < 0 || repeat > 1) {
                            System.out.println("Zadané číslo musí být 1 pro opakování, nebo 0 pro konec");
                            repeat = sc.nextInt();                        
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Pouze celá čísla prosíme" + e.getMessage());
                    System.out.println("Program se automaticky restartoval");
                    System.out.println("");
                    sc.nextLine();
                }
            } catch (IOException e) {
                System.out.println("Nastal problém" + e.getMessage());
            }
        }
    }
}
