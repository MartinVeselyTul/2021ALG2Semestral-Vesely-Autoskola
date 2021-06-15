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
public class UICodeSaver{

    /**
     * Metoda vypisující základní menu programu
     * @return vrací StringBuilder s menu
     */    
    public static String menu() {
        return "Výběrové menu (zmáčkněte prosím číslo vašeho výběru)"+"\n"+
                "1. výpis všech účastníků autoškoly"+"\n"+
                "2. výpis žáků, kteří uspěli"+"\n"+
                "3. výpis žáků, kteří prošli testy, ale nesplnili závěrečné jízdy"+"\n"+
                "4. výpis žáků, kteří nesplnili ani jednu část"+"\n"+
                "----------------------------------------------------------------"+"\n"+
                "Zadejte vaši volbu:";     
    }

    /**
     * Metoda vypisuje začátek programu
     * @return vrací StringBuilder s uvítáním uživatele do programu
     */
    public static String start() {
        return "Tento program vyhodnocuje, jací žáci udělali autoškolu." +"\n"+
                "Pro splnění autoškoly je třeba splnit teoretický test na min 44 bodů a následně splnit závěrečné zkoušky." +"\n"+
                "Vstupními daty jsou 2 soubory (údaje o teoretickém testu a údaje o závěrečných zkouškách)." +"\n"+
                "Výstupem programu je roztříděný seznam žáků dle jejich výsledků." +"\n"+
                " " +"\n"+ "Výstupní soubory můžete ukládat ve formátu .csv, .txt a .pdf";        
    }

    /**
     * Metoda vypisuje další menu pro uživatele
     * @return vrací StringBuilder s menu
     */
    public static String filtr() {
        return "Filtrování, vyberte ze seznamu způsob filtrování"+"\n"+
                " "+"\n"+
                "1. filtrování dle pohlaví (zobrazit jen ženy)"+"\n"+
                "2. filtrování dle pohlaví (zobrazit jen muže)"+"\n"+
                "3. filtrování dle počtu bodů (sestupně)"+"\n"+
                "4. filtrování dle počtu bodů (vzestupně)"+"\n"+
                "5. filtrování dle data narození (od nejstaršího)"+"\n"+
                "6. filtrování dle jména";        
    }
    
    
    public static String volbaNeboFiltrPraceSDaty(){
        return "Stikněte" +"\n"+ 
                "1. pro ukázku filtrování(Comparator, Comparable)" +"\n"+
                "2. pro ukázku práce s daty (počet uživatelů, co něco)";
    }
    
    public static String printHeader() {
        return String.format("%10s %10s %7s %10s %15s", "Jméno", "Přijímení", "Pohlaví", "Počet bodů", "Datum narození");
    }
    
    /**
     * Metoda vypisuje string, kde uživatel vidí v jakém formátu může tabulku uložit
     * @return vrací String s daty pro uživatele
     */
    public static String savingFormat() {
        return "Vyberte, v jaké formátu chcete soubor uložit." +"\n"+
                "\n" + "1 .txt" +"\n"+ "2 .csv" +"\n"+ "3 .pdf" +"\n"+ "4 .dat (binar)";        
    }
    
    public static String usersChoiceGroupOfDrivers(){
        return "Zvolte číslo pro výběr výpisu" +"\n"+
                "1. pro zobrazení jezdců, co udělali autoškolu" +"\n"+
                "2. pro zobrazení jezdců, co neudělali závěrečné jízdy" +"\n"+
                "3. pro zobrazení jezdců, co neudělali písemné testy" +"\n"+
                "4. pro zobrazení všech jezdců";
    }
    
    public static String usersChoiceDataWork(){
        return "Zvolte číslo pro výběr výpisu" +"\n"+
                "1. pro zobrazení počtu jezdců, co udělali autoškolu" +"\n"+
                "2. pro zobrazení počtu jezdců, co neudělali závěrečné jízdy" +"\n"+
                "3. pro zobrazení počtu jezdců, co neudělali písemné testy" +"\n"+
                "4. pro zobrazení počtu všech jezdců";                
    }
    
    public static String usersChoiceComparableCompatator(){
        return "Zvolte číslo pro výběr výpisu" +"\n"+
                "1. pro setřídění dle počtu bodů (sestupně)" +"\n"+
                "2. pro setřídění dle počtu bodů (vzestupně)" +"\n"+
                "3. pro setřídění dle křestního jména" +"\n"+
                "4. pro setřídění dle data";
    }
}
//testování jsem dělal přes DrivingSchool UI, abych zjistil, že i volání na metody funguje
