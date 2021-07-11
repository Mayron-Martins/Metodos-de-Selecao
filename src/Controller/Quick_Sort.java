/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;

/**
 *
 * @author Mayro
 */
public class Quick_Sort {
    private long timeQuick = 0;
    
    public long QuickSort(ArrayList<Integer> array,  int primeiro, int ultimo){
        long nanotime = System.nanoTime();
        
        int i = primeiro, f = ultimo;
        int x = (int) (Math.random()*(ultimo-primeiro+1))+primeiro;
        int pivo = array.get(x);
        
        while(i<=f){
            while(i<ultimo && array.get(i)<pivo){
                i++;
            }
            while(f>primeiro && array.get(f)>pivo){
                f--;
            }
            
            if(i<=f){
                x = array.get(f);
                array.set(f--, array.get(i));
                array.set(i++, x);
            }
        }
        
        if(primeiro<f){
            QuickSort(array, primeiro, f);
        }
        if(i<ultimo){
            QuickSort(array, i, ultimo);
        }
        
        timeQuick = System.nanoTime()-nanotime;
        return timeQuick;
    }

       
}
