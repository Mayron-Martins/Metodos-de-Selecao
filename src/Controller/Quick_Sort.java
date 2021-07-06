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
    
    public ArrayList<Integer> QuickSort(ArrayList<Integer> array,  int inicio, int fim){
        timeQuick = System.nanoTime();
        if (inicio < fim) {
            int posicaoPivo = separar(array, inicio, fim);
            QuickSort(array, inicio, posicaoPivo - 1);
            QuickSort(array, posicaoPivo + 1, fim);
        }
        timeQuick = System.nanoTime()-timeQuick;
        return array;
    }
    
       private int separar(ArrayList<Integer> vetor, int inicio, int fim) {
             int pivo = vetor.get(inicio);
             int i = inicio + 1, f = fim;
             while (i <= f) {
                    if (vetor.get(i) <= pivo){
                        i++;
                    } 
                    else{if (pivo < vetor.get(f)){
                        f--;
                    }        
                    else {
                           int troca = vetor.get(i);
                           vetor.set(i, vetor.get(f));
                           vetor.set(f, troca);
                           i++;
                           f--;
                    }
                    }
             }
             vetor.set(inicio, vetor.get(f));
             vetor.set(f, pivo);
             return f;
       }

    public long getTimeQuick() {
        return timeQuick;
    }
       
       
}
