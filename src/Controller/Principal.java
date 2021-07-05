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
    
    public Principal(){
        FilesGenerate gerarPastas = new FilesGenerate();
        gerarPastas.gerarPastas();
    }
    
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
        
        //joga o valor do array ordenado em arrayOrdenado
    }
    
    public void gerarGraficos(){
        //Pega os dados da tabela e gera um gráfico em outra tela
    }
    
    public void exportarDados(boolean medias){
        //Pega os dados da tabela, faz as médias e inicia a função de exportar para excel
        if(!medias){
            //dados da tabela principal
        }
        else{
            //dados da tabela de médias
        }
        
    }
    
    public void salvarMedia(){
        //Inicia a função de criação de txt, se não existir
        //Joga o valor das médias dentro do arquivo de txt
        //Joga o valor das médias nas tabelas de médias
    }
    
    public void exportarOrdenação(){
        //inicia a função de exportação com o arrayOrdenado
    }
}
