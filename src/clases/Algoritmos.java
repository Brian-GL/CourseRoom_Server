/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;


//import com.mathworks.engine.MatlabEngine;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
///**
// *
// * @author LENOVO
// */
//public class Algoritmos {
//    
//    public Algoritmos(){
//        try {
//            
//            Future<String[]> eFuture = MatlabEngine.findMatlabAsync();
//            String[] engines = eFuture.get();
//            Future<MatlabEngine> engFuture = MatlabEngine.connectMatlabAsync(engines[0]);
//            // Work on other thread
//            MatlabEngine eng = engFuture.get();
//            // Execute command on shared MATLAB session
//            Future<Void> vFuture = eng.evalAsync("plot(1:10); print('myPlot','-djpeg')");
//            eng.close();
//        } catch (IllegalArgumentException | IllegalStateException | ExecutionException | InterruptedException ex) {
//            System.err.println(ex.getMessage());
//        } 
//    }
//    
//}
