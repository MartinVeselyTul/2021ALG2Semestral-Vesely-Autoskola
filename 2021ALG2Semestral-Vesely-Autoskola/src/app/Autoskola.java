package app;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marti_000
 */
public class Autoskola {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    private List<Jezdec> drivers;
    
    public Autoskola(){
        drivers = new ArrayList<>();        
    }
    
    
}
