/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.DrivingSchool;

/**
 *
 * @author marti_000
 */
public interface InterfaceUI {
    public String menu();
    public String start();
    public String filtr();
    public String filtering(int n, DrivingSchool ds, int gender, int tableChoice);
    public String usersChoice(int n, DrivingSchool ds, int gender, int sort);
    public String savingFormat();
    
}
