package ui;

import app.DrivingSchool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marti_000
 */
public class DrivingSchoolUI {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        DrivingSchool ds = new DrivingSchool();
        try {
            while (true) {
                try {
                    System.out.println("Zadejte název souboru");
                    ds.loadResults("data/"+sc.next());
                    System.out.println(ds.printResults());
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Zadaný soubor neexistuje.");
                }
            }
        } catch (IOException e) {
            System.out.println("Nastal problém" + e.getMessage());
        }
    }
}
