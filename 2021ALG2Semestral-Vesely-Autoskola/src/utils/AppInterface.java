/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Driver;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MartinVesely
 */
public interface AppInterface {
    public void loadResults(String filename) throws FileNotFoundException, IOException;
    public String printDrivers();
    public void saveResults(String filename, List<Driver> list) throws IOException;
    public void loadDrivingTests(String filename) throws FileNotFoundException, IOException;
    public void saveResultsToBinary(File resultFile) throws FileNotFoundException, IOException;
    public void savePDF(String filename) throws FileNotFoundException, DocumentException;
    
}
