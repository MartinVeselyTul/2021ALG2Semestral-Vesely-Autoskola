package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import utils.AppInterface;

/*
 * Tato třída slouží pro načítání dat a následnou manipulaci s nimi.
 * Všechny metody jsou použity v UI k vypisování dat, nejčastěji Stringu s tabulkou účastníků autoškoly.
 * 
 */
/**
 *
 * @author MartinVesely
 */
public class DrivingSchool implements AppInterface {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<Driver> drivers;

    public DrivingSchool() {
        drivers = new ArrayList<>();
    }

    /**
     * Tato metoda načítá první soubor, ve kterém jsou čárkou oddělené informace
     * o jednotlivých účastnících autoškoly. První soubor obsahuje jméno,
     * přijímení, pohlaví, počet bodů z teoretického testu a datum narození.
     * Metoda třídí účastníky dle počtu bodů, pokud mají méně než 45, neprošli
     * teoretickými testy
     *
     * @param filename jméno hledaného soubory s daty
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
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

    /**
     * 
     *
     * @return vrací StringBuilder s tabulkou účastníků autoškoly
     */
    @Override
    public String printDrivers() {
        StringBuilder sb = new StringBuilder();
        for (Driver d : drivers) {
            sb.append(d).append("\n");
        }
        return sb.toString();
    }
    
    public String printPassedDrivers(){
        StringBuilder sb = new StringBuilder();
        for (Driver driver : drivers) {
            if(driver.getDrivingTest() == true){
                sb.append(driver).append("\n");
            }
        }
        return sb.toString();
    }
    
    public String printDidntPassedDriving(){
        StringBuilder sb = new StringBuilder();
        for (Driver driver : drivers) {
            if(driver.getDrivingTest() == false && driver.getTestPoints() > 44){
                sb.append(driver).append("\n");
            }
        }
        return sb.toString();
    }
    
    public String printDidntPassedTheory(){
        StringBuilder sb = new StringBuilder();
        for (Driver driver : drivers) {
            if(driver.getTestPoints() < 45){
                sb.append(driver).append("\n");
            }
        }
        return sb.toString();
    }

    public void sortByPoints(){
        Comparator c = new comparablePoints();
        Collections.sort(drivers,c);
    }
    
    public void sortByPointsUp(){
        Comparator c = new comparablePointsUp();
        Collections.sort(drivers,c);
    }
    
    public void sortByName(){
        Collections.sort(drivers);
    }
    
    public void sortByBirth(){
        Comparator c = new comparableBirth();
        Collections.sort(drivers, c);
    }
    
    public String getStatisticPassedDrivers(){
//        switch(n){
//            case 2:
//                drivers.subList(n, n)
//        }
        int averagePoints = 0;
        int driversCount =0;
        int femaleCount = 0;
        int maleCount = 0;
        for (Driver driver : drivers) {            
            if(driver.getDrivingTest() == true && driver.getTestPoints() > 44){                
                driversCount += 1;
                averagePoints += driver.getTestPoints();
                if(driver.getGender() == 'M'){
                    maleCount += 1;
                }else{
                    femaleCount += 1;
                }
            }
        }
        return "Statistiky jezdců, co udělali autoškolu" +"\n"+
                "Počet všech jezdců: " +driversCount +"\n"+
                "Počet žen: " +femaleCount+ "\n" +
                "Počet mužů: " +maleCount+ "\n" +
                "Průměrný počet bodů: " +averagePoints/driversCount +"\n";
    }
    public int getNumberPassedDrivers(){
        int sum = 0;
        for (Driver driver : drivers) {
            if(driver.getDrivingTest() == true){
                sum += 1;
            }
        }
        return sum;
    }
    
    public int getNumberDidntPassedDriving(){
        int numberDidntPassedD = 0;
        for (Driver driver : drivers) {
            if(driver.getDrivingTest() == false && driver.getTestPoints() > 44){
                numberDidntPassedD += 1;
            }
        }
        return numberDidntPassedD;
    }
    
    public int getNumberDidntPassedTheory(){
        int numberDidntPassedT = 0;
        for (Driver driver : drivers) {
            if(driver.getTestPoints() < 45){
                numberDidntPassedT += 1;
            }
        }
        return numberDidntPassedT;
    }
    
    /**
     * Metoda slouží k ukládání dat do souboru txt nebo csv Třídí dle vstupních
     * dat, jaký List má uložit
     *
     * @param filename parametr jméno souboru, volí uživatel
     * @param list
     * @throws java.io.IOException
     */
    @Override
    public void saveResults(String filename, List<Driver> list) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))))) {
            for (Driver driver : list) {
                pw.println(driver.toString());
            }

        }
    }

    /**
     * Metoda načítá druhý soubor s daty, který obsahuje jméno, přijímení a
     * boolean, zda účastníci uspěli v závěrečných zkouškách Metoda třídí
     * účastníky do Listů dle výsledků ze závěrečných zkoušek (boolean)
     *
     * @param filename jméno vstupního souboru
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
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
                for (Driver driver : drivers) {
                    if (id == driver.getId()) {
                        driver.setDrivingTest(driveTest);
                    }
                }
            }
        }
    }

    /**
     * Metoda slouží pro uložení dat do binárního souboru
     *
     * @param resultFile jméno souboru, volí uživatel
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void saveResultsToBinary(File resultFile) throws FileNotFoundException, IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFile))) {
            for (Driver driver : drivers) {
                dos.writeInt(driver.getTestPoints());
                dos.writeUTF(driver.getFirstName());
                int nChars = driver.getSecondName().length();
                dos.writeInt(nChars);
                for (int i = 0; i < nChars; i++) {
                    dos.writeChar(driver.getSecondName().charAt(i));
                }
            }
        }
    }

    /**
     * Metoda slouží k ukládání dat do souboru pdf Využita externí knihovna
     * iText
     *
     * @param filename parametr jméno souboru, volí uživatel
     * @throws java.io.FileNotFoundException
     * @throws com.itextpdf.text.DocumentException
     */
    @Override
    public void savePDF(String filename) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));

        com.itextpdf.text.List a = new com.itextpdf.text.List();
        for (Driver driver : drivers) {
            a.add(driver.toString());

            document.open();
            document.add(new Paragraph("Seznam požadovaných jezdcu"));
            document.add(a);
            document.close();

        }
    }
    public static void main(String[] args) throws IOException, DocumentException {
        DrivingSchool ds = new DrivingSchool();
        String nevimco = "results/dalsi";
        File file = new File(nevimco);
        file.getParentFile().mkdirs();
//        try{
//            ds.savePDF(nevimco);
//        }catch(FileNotFoundException e){
//            System.out.println("Nastala chyba"+ e.getMessage());
//        }
        System.out.println("Ahoj");
    }

}
