
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
        ArrayList<Integer> numList = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader (
                new FileInputStream(path)
                )
            );

            
            ArrayList<String> txt = new ArrayList<>();
            for (String line = reader.readLine(); line != null; line = reader.readLine())
                txt.add(line);
            reader.close();

            for (String num : txt)
                numList.add(Integer.parseInt(num));

        } catch (IOException e) {
            System.out.println("Erro ao ler aquivo!");

        } catch (NumberFormatException e) {
            System.out.println("ContÃ©m um valor numÃ©rico invÃ¡lido!");
        
        }

        return numList;
    }

}
