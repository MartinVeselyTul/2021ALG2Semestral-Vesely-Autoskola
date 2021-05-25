/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author marti_000
 */
public class UICodeSaver{
    
    public String menu(){
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
            if (i == 6){
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }
    
    public String start(){
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
            if (i == 6){
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }
    
    public String filtr(){
        String[] par = new String[8];
        par[0] = "Filtrování, vyberte ze seznamu způsob filtrování";
        par[1] = "";
        par[2] = "1. filtrování dle pohlaví";
        par[3] = "2. filtrování dle počtu bodů (sestupně)";
        par[4] = "3. filtrování dle počtu bodů (vzestupně)";
        par[5] = "4. filtrování dle data narození";
        par[6] = "5. filtrování dle jména";
        par[7] = "6. filtrování dle přijímení";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 7; i++) {            
            if (i == 7){
                sb.append(par[i]);
            } else {
                sb.append(par[i]).append("\n");
            }
        }
        return sb.toString();
    }
    
    public String usersChoice(int n, DrivingSchool ds, int gender){
        switch (n){                   
                case 1:
                    return "Účastníci autoškoly" + "\n"+
                    ds.printHeader() + "\n" +
                    ds.printAllDrivers(gender);                                        
                case 2:
                    return "Udělali autoškolu" + "\n" +
                    ds.printHeader() + "\n" +
                    ds.printPassedDrivers(gender);                   
                case 3:
                    return "Neudělali testy" + "\n" +
                    ds.printHeader() + "\n" +
                    ds.printDidintPassedTheory(gender);
                case 4:
                    return "Neudělali závěrečné jízdy" + "\n" +
                    ds.printHeader() + "\n" +
                    ds.printDidntPassedDriving(gender);
                default:
                    return "Do tohoto stavu jsme se dostat nechtěli. Obraťte se na naši zákaznickou podporu.";                    
            }
    }
//    public static void main(String[] args) {
//        System.out.println(Menu());
//    }
}
