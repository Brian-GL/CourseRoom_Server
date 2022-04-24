/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;



/**
 *
 * @author LENOVO
 */
public class Algoritmos {
    
    public Algoritmos(){
        try {
            MatlabEngine eng = MatlabEngine.startMatlab();
            double[] a = {2.0 ,4.0, 6.0};
            double[] roots = eng.feval("sqrt", a);
            for (double e: roots) {
                System.out.println(e);
            }
            eng.close();
        } catch (EngineException ex) {
            System.err.println(ex.getMessage());
        } catch (InterruptedException | IllegalArgumentException | IllegalStateException | RejectedExecutionException | ExecutionException ex) {
            System.err.println(ex.getMessage());
        }
       
    }
    
}
