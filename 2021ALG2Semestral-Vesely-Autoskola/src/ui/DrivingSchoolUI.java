package ui;

import app.DrivingSchool;
import app.UICodeSaver;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Upravy
 * 1. vše v UICodeSaver přepsat na sout + zkrátit kód v mainu - hotovo
 * 2. všechny stejné části kódu smazat a napsat jen jednou, dát do metod (DRY) - hotovo
 * 3. místo výpisu Driver vypsat data, které bude program počítat - hotovo (statistiky)
 * 4. optimalizovat - hotovo, zkráceno, napsané jiné metody a celá práce přepracována
 * 
 * 
 */
/**
 *
 * @author MartinVesely
 */
public class DrivingSchoolUI {

    static Scanner sc = new Scanner(System.in);
    static DrivingSchool ds = new DrivingSchool(); //pri deklaraci pres interface AppInterface ds = new DrivingSchool(); nemohu pak ds využívat (zeptat se proč)

    public static void main(String[] args) {
        do {
            try {
                nactiSoubory(ds);
                System.out.println(UICodeSaver.start());
                System.out.println("");
                System.out.println(UICodeSaver.menu());

                try {

                    try {

                        userChoiceMain();

                    } catch (FileNotFoundException | DocumentException ex) {
                        //Logger.getLogger(DrivingSchoolUI.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("V kódu nastala chyba " + ex);
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
            System.out.println("Pro opakování stiskněte 1");
        } while (sc.nextInt() == 1);
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

    public static void swichingDrivers(int n) {
        switch (n) {
            case 1:
                ds.getPassedDrivers();
                break;
            case 2:
                ds.getDidntPassedDriving();
                break;
            case 3:
                ds.getDidntPassedTheory();
                break;
            case 4:
                break;
        }
    }

    public static void userChoose1() throws IOException, FileNotFoundException, DocumentException {
        System.out.println(UICodeSaver.usersChoiceGroupOfDrivers());
        int choiceGroup = sc.nextInt();
        System.out.println(UICodeSaver.usersChoiceComparableCompatator());
        int choiceSort = sc.nextInt();

        swichingDrivers(choiceGroup);
        userChoiceSort(choiceSort);

        System.out.println(ds.printDrivers());
        savingFiles();
    }

    public static void userChoose2() {
        System.out.println(UICodeSaver.usersChoiceDataWork());
        int userWorkchoice = sc.nextInt();

        swichingDrivers(userWorkchoice);

        System.out.println(ds.getStatistic());
    }

    public static void userChoiceMain() throws IOException, FileNotFoundException, DocumentException {
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

    public static void savingFiles() throws IOException, FileNotFoundException, DocumentException {
        System.out.println(UICodeSaver.savingFormat());
        int n = sc.nextInt();
        System.out.println("Zadejte název souboru");
        String filename = sc.next();
        switch (n) {
            case 1:
                ds.saveResults(filename + ".txt");
                break;
            case 2:
                ds.saveResults(filename + ".csv");
                break;
            case 3:
                ds.savePDF(filename);
                break;
            case 4:
                ds.saveResultsToBinary(filename);
                break;
            default:
                System.out.println("Nastala chyba");
                break;
        }
    }
}
