/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author LENOVO
 */
public class Algoritmos {
    
    public Algoritmos(){
        try {
            
           // Start Matlab
            MatlabEngine eng = MatlabEngine.startMatlab();
           String ruta = "cd '" + System.getProperty("user.dir")  + "/src/scripts/ProyectoFinal.m'";

           System.out.println("Ruta: "+ruta);
           // Change directory and evaluate your function
           eng.eval(ruta);
           
           double[] roots = eng.feval("CNC", "Image_1.bmp", "Template.bmp");
           
            for (double e: roots) {
                System.out.println(e);
            }
           
           eng.close();
            
        } catch (EngineException ex) {
            System.err.println(ex.getMessage());
        } catch (InterruptedException | IllegalArgumentException | IllegalStateException | ExecutionException ex) {
            System.err.println(ex.getMessage());
        }
       
    }
    
}
