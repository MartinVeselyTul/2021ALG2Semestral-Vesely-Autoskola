/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author MartinVesely
 */
public interface AppInterface {
    public void loadResults(String filename) throws FileNotFoundException, IOException;
    public String printDrivers();
    public void getPassedDrivers();
    public void getDidntPassedDriving();
    public void getDidntPassedTheory();
    public void sortByPoints();
    public void sortByPointsUp();
    public void sortByName();
    public void sortByBirth();
    public String getStatistic();
    public void saveResults(String filename) throws IOException;
    public void loadDrivingTests(String filename) throws FileNotFoundException, IOException;
    public void saveResultsToBinary(String resultFile) throws FileNotFoundException, IOException;
    public void savePDF(String filename) throws FileNotFoundException, DocumentException;
    
    
}
