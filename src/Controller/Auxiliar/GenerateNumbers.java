
package Controller.Auxiliar;

import java.util.Random;

public class GenerateNumbers {
    
    
    int numero;
        int[] num = new int[10000];
        Random r = new Random();

        for(int i = 0; i<num.length; i++){
             numero = r.nextInt(20000) + 1;
             for(int j=0; j<num.length; j++){
                   if(numero == num[j] && j != i){
                         numero = r.nextInt(20000) + 1;
                   }else{
                        num[i] = numero;
                        
                   }
             }
        }
        
             //Apresentar na tela o resultado
            for(int i=0; i<num.length; i++){
                System.out.print(num[i]+"  \n");
            } 
        
}
