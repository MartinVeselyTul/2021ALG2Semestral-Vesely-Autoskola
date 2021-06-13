package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
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
public class DrivingSchool implements AppInterface{

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

    /**
     * Tato metoda načítá první soubor, ve kterém jsou čárkou oddělené informace o jednotlivých účastnících autoškoly.
     * První soubor obsahuje jméno, přijímení, pohlaví, počet bodů z teoretického testu a datum narození. 
     * Metoda třídí účastníky dle počtu bodů, pokud mají méně než 45, neprošli teoretickými testy
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
                if (d.getTestPoints() < 45) {
                    didntPassedTheory.add(d);
                }

            }
        }
    }

    /**
     * Tato metoda vypisuje první soubor, nic do něj není přidáno.
     * Metoda filtruje účastníky dle toho, co si uživatel zvolí v UI.
     * Filtrace je založena na výběru dle pohlaví.
     * Metoda sortuje účastníky dle výběru uživatele.
     * Sortování je provedeno přes Comparable i Comparator.
     * Sortuje dle bodů sestupně, vzestupně a dle křestního jména dle české abecedy.
     * 
     * @param n parametr pro filtrování dle pohlaví
     * @param compare parametr pro sortování dle uživatele
     * @return vrací StringBuilder s tabulkou účastníků autoškoly
     */
    @Override
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

    /**
     * Metoda, co vypisuje účastníky autoškoly, kteří splnili teoretické testy tj. mají více než 45 bodů a splnili závěrečné jízdy -> mají autoškolu
     * 
     * Metoda filtruje účastníky dle toho, co si uživatel zvolí v UI.
     * Filtrace je založena na výběru dle pohlaví.
     * Metoda sortuje účastníky dle výběru uživatele.
     * Sortování je provedeno přes Comparable i Comparator.
     * Sortuje dle bodů sestupně, vzestupně a dle křestního jména dle české abecedy.
     * 
     * @param n parametr pro filtrování dle pohlaví
     * @param compare parametr pro sortování dle uživatele
     * @return vrací StringBuilder s tabulkou účastníků autoškoly
     */
    @Override
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

    /**
     * Teto metoda vypisuje nadpis tabulky Jméno, Přijímení, Pohlaví, Počet bodů a Datum narození
     * @return metoda vrací String se záhlavím tabulky
     */
    @Override
    public String printHeader() {
        return String.format("%10s %10s %7s %10s %15s", "Jméno", "Přijímení", "Pohlaví", "Počet bodů", "Datum narození");
    }

    /**
     * Metoda slouží k ukládání dat do souboru txt nebo csv
     * Třídí dle vstupních dat, jaký List má uložit
     * 
     * @param filename parametr jméno souboru, volí uživatel
     * @param choice parametr, který předává UI, aby metoda věděla, jakou tabulku uložit
     * @throws java.io.IOException
     */
    @Override
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

    /**
     * Metoda načítá druhý soubor s daty, který obsahuje jméno, přijímení a boolean, zda účastníci uspěli v závěrečných zkouškách
     * Metoda třídí účastníky do Listů dle výsledků ze závěrečných zkoušek (boolean)
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
                if (driveTest == true) {
                    for (Driver r : drivers) {
                        if (r.getId() == id) {                            
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

    /**
     * Tato metoda vypisuje účastníky, kteří nenapsali teoretické testy tj. měli méně než 45 bodů
     * Metoda filtruje účastníky dle toho, co si uživatel zvolí v UI.
     * Filtrace je založena na výběru dle pohlaví.
     * Metoda sortuje účastníky dle výběru uživatele.
     * Sortování je provedeno přes Comparable i Comparator.
     * Sortuje dle bodů sestupně, vzestupně a dle křestního jména dle české abecedy.
     * 
     * @param n parametr pro filtrování dle pohlaví
     * @param compare parametr pro sortování dle uživatele
     * @return vrací StringBuilder s tabulkou účastníků autoškoly
     */
    @Override
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

    /**
     * Tato metoda vypisuje účastníky, kteří mají více než 45 bodů, ale neuspěli v závěrečných zkouškách
     * Metoda filtruje účastníky dle toho, co si uživatel zvolí v UI.
     * Filtrace je založena na výběru dle pohlaví.
     * Metoda sortuje účastníky dle výběru uživatele.
     * Sortování je provedeno přes Comparable i Comparator.
     * Sortuje dle bodů sestupně, vzestupně a dle křestního jména dle české abecedy.
     * 
     * @param n parametr pro filtrování dle pohlaví
     * @param compare parametr pro sortování dle uživatele
     * @return vrací StringBuilder s tabulkou účastníků autoškoly
     */
    @Override
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
    public void changeList(int n, List<Driver> list){
        switch(n){
            case 1:
                list = drivers;
                break;
            case 2:
                list = passedTests;
                break;
            case 3:
                list = didntPassedTheory;
                break;
            case 4:
                list = didntPassedDriving;
                break;
            default:
                System.out.println("Obraťte se na podporu");
                break;
        }
    }
    public void saveResultsToBinary(File resultFile, List<Driver> list) throws FileNotFoundException, IOException{
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFile))){
            for (Driver driver : list) {
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
     * Metoda slouží k ukládání dat do souboru pdf
     * momentálně nefunkční kvůli ArrayListu z knihovny java.util, která není kompatibilní s knihovnou iText
     * bude se muset využít jiná knihovna
     * 
     * @param filename parametr jméno souboru, volí uživatel
     * @param n
     * @throws java.io.FileNotFoundException
     * @throws com.itextpdf.text.DocumentException
     */
    public void savePDF(String filename, int n) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream(filename+".pdf"));
        
        com.itextpdf.text.List a = new com.itextpdf.text.List();
        switch(n){
            case 1:
                for (Driver driver : drivers) {
                a.add(driver.toString());  
                }
                break;
            case 2:
                for (Driver driver : passedTests) {
                a.add(driver.toString());  
                }
                break;
            case 3:
                for (Driver driver : didntPassedTheory) {
                a.add(driver.toString());  
                }
                break;
            case 4:
                for (Driver driver : didntPassedDriving) {
                a.add(driver.toString());  
                }
                break;
            default:
                System.out.println("Obraťte se na podporu");
                break;
        }
        

        document.open();
        document.add(new Paragraph("Seznam požadovaných jezdcu"));
        document.add(a);
        document.close();
        
            
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
 