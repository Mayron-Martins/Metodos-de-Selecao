/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author adrya
 */
public class Leitor {
        public ArrayList<Integer> leitor(String path) {
        var numList = new ArrayList<Integer>();
        
        try {
            var reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(path)
                )
            );

            
            var txt = new ArrayList<String>();
            for (var line = reader.readLine(); line != null; line = reader.readLine())
                txt.add(line);
            reader.close();

            for (var num : txt)
                numList.add(Integer.parseInt(num));

        } catch (IOException e) {
            System.out.println("Erro ao ler aquivo!");

        } catch (NumberFormatException e) {
            System.out.println("ContÃ©m um valor numÃ©rico invÃ¡lido!");
        
        }

        return numList;
    }

}
