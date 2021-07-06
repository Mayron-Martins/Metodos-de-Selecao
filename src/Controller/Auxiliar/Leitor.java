
package Controller.Auxiliar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author adrya
 */
public class Leitor {
        public ArrayList<Integer> leitor() {
            File enderecoArquivo=null;
            boolean existenciaArquivo = false;
                    
            FileNameExtensionFilter extensao = new FileNameExtensionFilter("Arquivos do Texto", "txt");
            JFileChooser arquivoEscolhido = new JFileChooser();
            arquivoEscolhido.setFileFilter(extensao);
            arquivoEscolhido.setDialogTitle("Selecione o arquivo para ordenação");
            arquivoEscolhido.setFileSelectionMode(JFileChooser.FILES_ONLY);
            arquivoEscolhido.setMultiSelectionEnabled(false);
            int resposta = arquivoEscolhido.showOpenDialog(null);

            if(resposta == JFileChooser.APPROVE_OPTION){
                enderecoArquivo = new File(arquivoEscolhido.getSelectedFile().getAbsolutePath());
                existenciaArquivo = true;
            }
            
            if(existenciaArquivo){
                ArrayList<Integer> numList = new ArrayList<>();

                try {
                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader (
                        new FileInputStream(enderecoArquivo.getPath())
                        )
                    );


                    ArrayList<String> txt = new ArrayList<>();
                    for (String line = reader.readLine(); line != null; line = reader.readLine()){
                        txt.add(line);
                    }   
                    reader.close();

                    for (String num : txt){
                        numList.add(Integer.parseInt(num));
                    }
                        

                } catch (IOException e) {
                    System.out.println("Erro ao ler aquivo!");

                } catch (NumberFormatException e) {
                    System.out.println("Contém um valor numérico inválido!");

                }

                return numList;
            }
            return null;
    }

}
