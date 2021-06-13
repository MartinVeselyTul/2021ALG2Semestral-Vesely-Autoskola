/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author MartinVesely
 */
public interface AppInterface {
    public void loadResults(String filename) throws FileNotFoundException, IOException;
    public String printAllDrivers(int n, int compare);
    public String printPassedDrivers(int n, int compare);
    public String printHeader();
    public void saveResults(String filename, int choice) throws IOException;
    public void loadDrivingTests(String filename) throws FileNotFoundException, IOException;
    public String printDidintPassedTheory(int n, int compare);
    public String printDidntPassedDriving(int n, int compare);
    
}
