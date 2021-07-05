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
public class Principal {
    private ArrayList <Integer> array = new ArrayList<>();
    private ArrayList <Integer> arrayOrdenado = new ArrayList<>();
    
    public void limparTabelas(){
        //Pega as tabelas e limpa os campos
    }
    public void gerarNumeros(){
        //Pega a quantidade de números a seres gerados
        //Inicia a função de geração de números com retorno do array
        //Joga o valor da função em array
    }
    
    public void importarNumeros(){
        //Inicia a função de importação de valores
        //Joga o valor em array
        //Implementa a função Thread de Leitura de cada método de seleção
    }
    
    public void implementarOrdenacao(){
        //limpar tabelas
        //Verifica e pega o valor do array
        Quick_Sort quickSorte = new Quick_Sort();
        Selection_Sort selectionSort = new Selection_Sort();
        
        for(int repet=0; repet<50; repet++){
            //Realiza a operação de ordenação com o Selection
            //Coloca o valor de tempo no campo correspondente
            
            //Realiza a operação de ordenação com Quick
            //Coloca o valor de tempo no campo correspondente
        }
        
        //Inicia a função de exportação do array para pasta designada
    }
}
