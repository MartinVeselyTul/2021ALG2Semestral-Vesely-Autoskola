/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import utils.InterfaceUI;


/**
 *
 * @author MartinVesely
 */
public class UICodeSaver implements InterfaceUI{

    /**
     *
     * @return
     */
    @Override
    public String menu() {
        String[] par = new String[7];
        par[0] = "Výběrové menu (zmáčkněte prosím číslo vašeho výběru)";
        par[1] = "1. výpis všech účastníků autoškoly";
        par[2] = "2. výpis žáků, kteří uspěli";
        par[3] = "3. výpis žáků, kteří prošli testy, ale nesplnili závěrečné jízdy";
        par[4] = "4. výpis žáků, kteří nesplnili ani jednu část";
        par[5] = "----------------------------------------------------------------";
        par[6] = "Zadejte vaši volbu:";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 6; i++) {
            if (i == 6) {
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String start() {
        String[] par = new String[7];
        par[0] = "Tento program vyhodnocuje, jací žáci udělali autoškolu.";
        par[1] = "Pro splnění autoškoly je třeba splnit teoretický test na min 44 bodů a následně splnit závěrečné zkoušky.";
        par[2] = " ";
        par[3] = "Vstupními daty jsou 2 soubory (údaje o teoretickém testu a údaje o závěrečných zkouškách).";
        par[4] = "Výstupem programu je roztříděný seznam žáků dle jejich výsledků.";
        par[5] = " ";
        par[6] = "Výstupní soubory můžete ukládat ve formátu .csv, .txt a .pdf";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 6; i++) {
            if (i == 6) {
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String filtr() {
        String[] par = new String[8];
        par[0] = "Filtrování, vyberte ze seznamu způsob filtrování";
        par[1] = "";
        par[2] = "1. filtrování dle pohlaví (zobrazit jen ženy)";
        par[3] = "2. filtrování dle pohlaví (zobrazit jen muže)";
        par[4] = "3. filtrování dle počtu bodů (sestupně)";
        par[5] = "4. filtrování dle počtu bodů (vzestupně)";
        par[6] = "5. filtrování dle data narození (od nejstaršího)";
        par[7] = "6. filtrování dle jména";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 7; i++) {
            if (i == 7) {
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }
    
    @Override
    public String filtering(int n, DrivingSchool ds, int gender, int tableChoice){
        tableChoice = tableChoice - 1;
        int sort = 0;
        
        if(n == 1){
            gender = 2;
        }
        if(n == 2){
            gender = 3;
        }
        if(n == 3){
            sort = 1;
        }
        if(n == 4){
            sort = 2;
        }
        if (n == 5){
            sort = 4;
        }
        if(n == 6){
            sort = 3;
        }
        String[] tabChoice = new String[4];
        tabChoice[0] = ds.printAllDrivers(gender, sort);
        tabChoice[1] = ds.printPassedDrivers(gender, sort);
        tabChoice[2] = ds.printDidintPassedTheory(gender, sort);
        tabChoice[3] = ds.printDidntPassedDriving(gender, sort);        
        
        String[] tabName = new String[4];
        tabName[0] = "Účastníci autoškoly";
        tabName[1] = "Udělali autoškolu";
        tabName[2] = "Neudělali písemné testy";
        tabName[3] = "Neudělali závěrečné jízdy";
        
        switch (n) {
            case 1:
                return tabName[tableChoice] + "\n"
                        + ds.printHeader() + "\n"
                        + tabChoice[tableChoice];
            case 2:
                return tabName[tableChoice] + "\n"
                        + ds.printHeader() + "\n"
                        + tabChoice[tableChoice];
            case 3:                
                return tabName[tableChoice] + "\n"
                        + ds.printHeader() + "\n"
                        + tabChoice[tableChoice];
            case 4:
                return tabName[tableChoice] + "\n"
                        + ds.printHeader() + "\n"
                        + tabChoice[tableChoice];
            case 5:
                return tabName[tableChoice] + "\n"
                        + ds.printHeader() + "\n"
                        + tabChoice[tableChoice];
            case 6:
                return tabName[tableChoice] + "\n"
                        + ds.printHeader() + "\n"
                        + tabChoice[tableChoice];
            default:
                return "Do tohoto stavu jsme se dostat nechtěli. Obraťte se na naši zákaznickou podporu.";
        }        
    }

    /**
     *
     * @param n
     * @param ds
     * @param gender
     * @param sort
     * @return
     */
    @Override
    public String usersChoice(int n, DrivingSchool ds, int gender, int sort) {        
        switch (n) {
            case 1:
                return "Účastníci autoškoly" + "\n"
                        + ds.printHeader() + "\n"
                        + ds.printAllDrivers(gender, sort);
            case 2:
                return "Udělali autoškolu" + "\n"
                        + ds.printHeader() + "\n"
                        + ds.printPassedDrivers(gender, sort);
            case 3:
                return "Neudělali testy" + "\n"
                        + ds.printHeader() + "\n"
                        + ds.printDidintPassedTheory(gender, sort);
            case 4:
                return "Neudělali závěrečné jízdy" + "\n"
                        + ds.printHeader() + "\n"
                        + ds.printDidntPassedDriving(gender, sort);
            default:
                return "Do tohoto stavu jsme se dostat nechtěli. Obraťte se na naši zákaznickou podporu.";
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String savingFormat() {
        String[] par = new String[5];
        par[0] = "Vyberte, v jaké formátu chcete soubor uložit.";
        par[1] = " ";
        par[2] = "1 .txt";
        par[3] = "2 .csv";
        par[4] = "3 .pdf";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 4; i++) {
            if (i == 4) {
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }
// main jsem využíval před vytvořením interface
//    public static void main(String[] args) {
//        //testovani vypisu stringu pro hlavni UI
//        
//        System.out.println(menu());
//        System.out.println("");
//        System.out.println(start());
//        System.out.println("");
//        System.out.println(filtr());
//        System.out.println("");
//        System.out.println(savingFormat());
//
//    }
}
//testování jsem dělal přes DrivingSchool UI, abych zjistil, že i volání na metody funguje
