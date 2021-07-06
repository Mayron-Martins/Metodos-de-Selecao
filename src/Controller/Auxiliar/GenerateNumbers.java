
package Controller.Auxiliar;

import java.util.ArrayList;
import java.util.Random;

public class GenerateNumbers {
    
    public ArrayList<Integer> gerarNumeros(int quantidade){
        int numero;
        ArrayList<Integer> array = new ArrayList<>();
        Random r = new Random();

        for(int i = 0; i<quantidade; i++){
             numero = r.nextInt(quantidade*2) + 1;
             if(array.contains(numero)){
                 i--;
             }
             else{
                 array.add(numero);
             }
        }
        if(array!=null){
            ExportDocuments exportar = new ExportDocuments();
            FilesGenerate file = new FilesGenerate();
            exportar.geraArrayTxt(array, System.getProperty("user.home")+"/documents/Métodos de Ordenação/Números Gerados/"+
                    array.size()+"N"+file.dataEHoraCodificada()+".txt");
        }
        return array;
    } 
        
}
