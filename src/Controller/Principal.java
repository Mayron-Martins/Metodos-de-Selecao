/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Auxiliar.ExportDocuments;
import Controller.Auxiliar.FilesGenerate;
import Controller.Auxiliar.Leitor;
import View.Desktop;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mayro
 */
public class Principal {
    private final Desktop view;
    private final DefaultTableModel tabelaSelection;
    private final DefaultTableModel tabelaQuick;
    private ArrayList <Integer> array = new ArrayList<>();
    private ArrayList <Integer> arrayOrdenado = new ArrayList<>();
    
    public Principal(Desktop view){
        this.view = view;
        tabelaSelection = (DefaultTableModel) view.getTabelaSelection().getModel();
        tabelaQuick = (DefaultTableModel) view.getTabelaQuick().getModel();
        FilesGenerate gerarPastas = new FilesGenerate();
        gerarPastas.gerarPastas();
    }
    
    public void limparTabelas(){
        int quantLinhas = view.getTabelaQuick().getRowCount();
        for(int i=0; i<quantLinhas; i++){
            tabelaQuick.removeRow(0);
        }
        quantLinhas = view.getTabelaSelection().getRowCount();
        for(int i=0; i<quantLinhas; i++){
            tabelaSelection.removeRow(0);
        }
    }
    public void gerarNumeros(){
        //Pega a quantidade de números a seres gerados
        //Inicia a função de geração de números com retorno do array
        //Joga o valor da função em array
    }
    
    public void importarNumeros(){
        Leitor ler = new Leitor();
        array = ler.leitor();
        if(array!=null){
            implementarOrdenacao();
        }
    }
    
    public void implementarOrdenacao(){
        limparTabelas();
        if(array!=null){
            Quick_Sort quickSorte = new Quick_Sort();
            Selection_Sort selectionSort = new Selection_Sort();

            for(int repet=0; repet<50; repet++){
                selectionSort.SelectionSort(array);
                Object dados[]={repet+1, array.size(), selectionSort.getTimeSelection()};
                tabelaSelection.addRow(dados);

                quickSorte.QuickSort(array, 0, array.size()-1);
                Object dados2[]={repet+1, array.size(), selectionSort.getTimeSelection()};
                tabelaSelection.addRow(dados2);
            }

            //joga o valor do array ordenado em arrayOrdenado
        }
        
    }
    
    public void gerarGraficos(){
        //Pega os dados da tabela e gera um gráfico em outra tela
    }
    
    public void exportarDados(){
        int linhasQuick = tabelaQuick.getRowCount();
        int linhaSelection = tabelaSelection.getRowCount();
        if(linhasQuick>0||linhaSelection>0){
            String interacoes = "50";
            String quantValores = ""+array.size();
            
            
            BigDecimal timeQuick = new BigDecimal(0);
            BigDecimal timeSelection = new BigDecimal(0);
            String dadoTabelaQuick;
            String dadoTabelaSelection;
            
            
            for(int i=0; i<linhasQuick; i++){
                dadoTabelaQuick = tabelaQuick.getValueAt(i, 2).toString();
                timeQuick = timeQuick.add(new BigDecimal(dadoTabelaQuick));
            }
            timeQuick = timeQuick.divide(new BigDecimal(linhasQuick));
            
            for(int i=0; i<linhaSelection; i++){
                dadoTabelaSelection = tabelaSelection.getValueAt(i, 2).toString();
                timeSelection = timeSelection.add(new BigDecimal(dadoTabelaSelection));
            }
            timeSelection = timeSelection.divide(new BigDecimal(linhaSelection));

            ExportDocuments exportar = new ExportDocuments();
            //Selection
            exportar.exportarExcel(view.getTabelaSelection().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", interacoes, quantValores, timeSelection.toString());

            //Quick
            exportar.exportarExcel(view.getTabelaQuick().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", interacoes, quantValores, timeQuick.toString());
        }
    }
    
    public void visualizarMedias(){
        
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
