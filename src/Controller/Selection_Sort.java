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
public class Selection_Sort {
    private long timeSelection = 0;
    
    public ArrayList<Integer> SelectionSort(ArrayList <Integer> array){
        long nanotime = System.nanoTime();
        for (int fixo = 0; fixo < array.size() - 1; fixo++) {
        int menor = fixo;
            for (int i = menor + 1; i < array.size(); i++) {
               if (array.get(i) < array.get(menor)) {
                  menor = i;
               }
            }
            if (menor != fixo) {
              int t = array.get(fixo);
              array.set(fixo, array.get(menor));
              array.set(menor, t);
            }
      }
        timeSelection = System.nanoTime()-nanotime;
        return array;
    }

    public long getTimeSelection() {
        return timeSelection;
    }
}
