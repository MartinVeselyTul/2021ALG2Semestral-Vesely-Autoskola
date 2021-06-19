package app;

/**
 * V této třídě se nachází pouze statické výpisy užité v UI, vše předěláno klasického String() -> return
 * @author MartinVesely
 */
public class UICodeSaver{

    public static String menu() {
        return "Výběrové menu (zmáčkněte prosím číslo vašeho výběru)"+"\n"+
                "1. výpis všech účastníků autoškoly"+"\n"+
                "2. výpis žáků, kteří uspěli"+"\n"+
                "3. výpis žáků, kteří prošli testy, ale nesplnili závěrečné jízdy"+"\n"+
                "4. výpis žáků, kteří nesplnili ani jednu část"+"\n"+
                "----------------------------------------------------------------"+"\n"+
                "Zadejte vaši volbu:";     
    }

    public static String start() {
        return "Tento program vyhodnocuje, jací žáci udělali autoškolu." +"\n"+
                "Pro splnění autoškoly je třeba splnit teoretický test na min 44 bodů a následně splnit závěrečné zkoušky." +"\n"+
                "Vstupními daty jsou 2 soubory (údaje o teoretickém testu a údaje o závěrečných zkouškách)." +"\n"+
                "Výstupem programu je roztříděný seznam žáků dle jejich výsledků." +"\n"+
                " " +"\n"+ "Výstupní soubory můžete ukládat ve formátu .csv, .txt a .pdf";        
    }
    
    public static String volbaNeboFiltrPraceSDaty(){
        return "Stikněte" +"\n"+ 
                "1. pro ukázku filtrování(Comparator, Comparable)" +"\n"+
                "2. pro ukázku práce s daty (počet uživatelů, co něco)";
    }
    
    public static String printHeader() {
        return String.format("%10s %10s %7s %10s %15s", "Jméno", "Přijímení", "Pohlaví", "Počet bodů", "Datum narození");
    }
    
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
        return "Zvolte číslo pro výběr statistik" +"\n"+
                "1. pro zobrazení statistiky jezdců, co udělali autoškolu" +"\n"+
                "2. pro zobrazení statistiky jezdců, co neudělali závěrečné jízdy" +"\n"+
                "3. pro zobrazení statistiky jezdců, co neudělali písemné testy" +"\n"+
                "4. pro zobrazení statistiky všech jezdců";                
    }
    
    public static String usersChoiceComparableCompatator(){
        return "Zvolte číslo pro výběr výpisu" +"\n"+
                "1. pro setřídění dle počtu bodů (sestupně)" +"\n"+
                "2. pro setřídění dle počtu bodů (vzestupně)" +"\n"+
                "3. pro setřídění dle křestního jména" +"\n"+
                "4. pro setřídění dle data";
    }
}
