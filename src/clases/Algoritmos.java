/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import static java.awt.SystemColor.text;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
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
    


/**
 *
 * @author Erick
 */

    public void Estructura_De_Datos_Mev() {
        iterator = block.listIterator();
        ArrayList<HashMap<String, Double>> mevs = new ArrayList();
        HashMap<String, Double> mev;
        while (iterator.hasNext()) {
            mevs = new HashMap();
            text = (String) iterator.next();
            words = text.split("\\s");
            for (String word : words) {
                if (word.contains(".")) {
                    word = word.substring(0, word.lastIndexOf('.'));
                }
                if (word.length() > 0) {
                    if (mev.containsKey(word)) {
                        mev.put(word, 1.0 + (double) mev.get(word));
                    } else {
                        mev.put(word, 1.0);
                    }
                }
            }
            mevs.add(mev);
        }
    }
    
    public double Comparacion_Entre_Vectores_De_Mev() {
        compareMev(List < HashMap < String, Double >> suspMev, List < HashMap < String, Double >> srcMev)
        {
            HashMap< String, Double> srcBlock;
            HashMap< String, Double> suspBlock;
            double max = 0;
            double cosine;

            Iterator srcIterator;
            Iterator suspIterator = suspMev.iterator();
            while (suspIterator.hasNext()) {
                suspBlock = (HashMap) suspIterator.next();
                srcIterator = srcMev.iterator();
                while (srcIterator.hasNext()) {
                    srcBlock = (HashMap) = srcIterator.next();
                    cosine = cosineMev(suspBlock, srcBlock);
                    if (cosine > max) {
                        max = cosine;
                    }
                }
            }
            return max;
        }
        
     cosineMev(HashMap < String, Double > mev1, HashMap < String, Double > mev2){
        Set<String> terms = new HashSet();
        terms.addAll(mev1.keySet());
        terms.addAll(mev2.keySet());
        Iterator iterator  = terms.iterator();
        double numerator = 0.0, denominator1 = 0.0, denominator2 = 0.0, v1, v2;
        String term;
        while(iterator.hasNext()){
            term = (String) iterator.next();
            v1 = (double) mev1.getOrDefault(term, 0.0);
            v2 = (double) mev2.getOrDefault(term, 0.0);
            numerador += v1 * v2;
            denominator1 +- Math.pow(v1, 2); denominator2 +- Math.pow(v2, 2);
        }
        return numerator/(Math.sqrt(denominator1)*Math.sqrt(denominator2));
    }
    }
    
    public void Estructura_De_Datos_De_EHD() throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        iterator = block.listIterator();
        ArrayList<byte[]> blockMinutia;
        boolean foundPeriod = false;
        byte[] minutia;
        while(iterator().hasNext()){
            blockMinutia = new ArrayList();
            text = (String) iterator.next();
            words = text.split("\\s");
            text = "";
            for(int j=0; j<words.length(); j++){
                if((j+minutiaSize) < words.length){
                    for(int k=0; k<minutiaSize; ++k){
                        foundPeriod = (words[j+k].contains(".") && words[j+k].length() > 1
                                && k != minutiaSize -1);
                        if(foundPeriod){
                            j = j+k;
                            k = minutiaSize;
                        }else{
                            if(words[j+k].contains(".") && words[j+k].length > 1){
                                text += " " +words[j+k].substring(0, words[j+k].lastIndexOf('.'));
                            }else{
                                text += " " +words[j+k];
                            }
                        }
                    }
                    if(!foundPeriod){
                        md.update(text.trim().getBytes("UTF-8"));
                        minutia = md.digest();
                        if(!findMinutia(blockMinutia, minutia)){
                            blockMinutia.add(minutia);
                        }
                    }
                    text = "";
                }
            }
            fingerprint.add(blockMinutia);
        }
    }
    
    public void Comparacion_Entre_Huellas_De_EHD(){
        compareFingerprints(ArrayList<ArrayList<byte[]>> suspFp, ArrayList<ArrayList<byte>> srcFp){
        ArrayList<byte[]> block;
        Iterator blockIterator = suspFp.listIterator();
        ListIterator minutiaIterator;
        
        Integer[] maxSuspMatches = new Integer[suspFp.size()];
        Integer[] srcBlockMatches = new Integer[srcFp.size()];
        
        int suspBlock = 0;
        int srcBlock;
        
        byte[] minutiae;
        
        while(blockIterator.hasNext()){
            block = (ArrayList<byte[]>) blockIterator.next();
            minutiaIterator = block.listIterator();
            Arrays.fill(srcBlockMatches, 0);
            while(minutiaIterator.hasNext()){
                srcBlock = 0;
                minutiae = (byte[]) minutiaIterator.next();
                for(boolean found: findMinutiae(minutiae, srcFp)){
                    if(found){
                        ++srcBlockMatches[srcBlock];
                    }
                    ++srcBlock;
                }
            }
            maxSuspMatches[suspBlock] =  Collections.max(Arrays.asList(srcBlockMatches));
            ++suspBlock;
        }
        return Collections.max(Arrays.asList(maxSuspMatches);
    }
        
    findMinutiae(byte[] suspMinutiae, ArrayList<ArrayList<byte[]>> srcFp){
        boolean[] found = new boolean[srcFp.size()];
        byte[] srcMinutiae;
        int i=0;
        
        ArrayList<byte[]> blockMinutia;
        Iterator iterator = srcFp.listIterator();
        ListIterator minutiaIterator;
        while(iterator.hasNext()){
            blockMinutia = (ArrayList<byte[]>) iterator.next();
            minutiaIterator = blockMinutia.listIterator();
            while(minutiaIterator.hasNext() && !found[i]){
                srcMinutiae = (byte[]) minutiaIterator.next();
                if(Arrays.equals(suspMinutiae, srcMinutiae)){
                    found[i] = true;
                }
            }
            ++i;
        }
        return found;
    }
    }


    
}
