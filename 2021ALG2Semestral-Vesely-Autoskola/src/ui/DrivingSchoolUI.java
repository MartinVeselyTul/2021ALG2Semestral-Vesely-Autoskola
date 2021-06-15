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
import utils.AppInterface;

/* Upravy
 * 1. vše v UICodeSaver přepsat na sout + zkrátit kód v mainu
 * 2. všechny stejné části kódu smazat a napsat jen jednou, dát do metod (DRY)
 * 3. místo výpisu Driver vypsat data, které bude program počítat
 * 4. optimalizovat
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
    static DrivingSchool ds = new DrivingSchool();

    public static void main(String[] args) {
        try {
            nactiSoubory(ds);
            System.out.println(UICodeSaver.start());
            System.out.println("");
            System.out.println(UICodeSaver.menu());

            try {
                userChoiceMain();
                
                //možnost výpisu seznamů funguje
                //dodělat výpisy dat a počtu u 2. možnosti
                
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

    public static void nactiSoubory(DrivingSchool ds) throws IOException {
        try {
            ds.loadResults("data/autoskola_testy.csv");
            ds.loadDrivingTests("data/driveTest.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Zadaný soubor neexistuje.");
        }
    }

    public static void userChoiceSort(int n) {
        switch (n) {
            case 1:
                ds.sortByPoints();
                break;
            case 2:
                ds.sortByPointsUp();
                break;
            case 3:
                ds.sortByName();
                break;
            case 4:
                ds.sortByBirth();
                break;
            default:
                System.out.println("Nastala chyba");
                break;
        }
    }

    public static void userChoose1() {
        System.out.println(UICodeSaver.usersChoiceGroupOfDrivers());
        int choiceGroup = sc.nextInt();
        System.out.println(UICodeSaver.usersChoiceComparableCompatator());
        int choiceSort = sc.nextInt();
        switch (choiceGroup) {
            case 1:
                userChoiceSort(choiceSort);
                
                System.out.println(ds.printPassedDrivers());
                break;
            case 2:
                userChoiceSort(choiceSort);
                System.out.println(ds.printDidntPassedDriving());
                break;
            case 3:
                userChoiceSort(choiceSort);
                System.out.println(ds.printDidntPassedTheory());
                break;
            case 4:
                userChoiceSort(choiceSort);                
                System.out.println(ds.printDrivers());
                break;
        }
    }

    public static void userChoose2() {
        System.out.println(UICodeSaver.usersChoiceDataWork());
        int userWorkchoice = sc.nextInt();
        System.out.println(ds.getStatisticPassedDrivers());
    }

    public static void userChoiceMain() {
        System.out.println(UICodeSaver.volbaNeboFiltrPraceSDaty());
        int n = sc.nextInt();
        switch (n) {
            case 1:
                userChoose1();
                break;
            case 2:
                userChoose2();
                break;
            default:
                System.out.println("Nastala chyba");
                break;
        }
    }
}
