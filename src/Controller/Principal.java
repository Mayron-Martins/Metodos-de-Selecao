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
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mayro
 */
public class Principal {
    protected final Desktop view;
    protected final DefaultTableModel tabelaSelection;
    protected final DefaultTableModel tabelaQuick;
    protected ArrayList <Integer> array = new ArrayList<>();
    protected ArrayList <Integer> arrayOrdenado = new ArrayList<>();
    protected final FilesGenerate gerarPastas = new FilesGenerate();
    private int interacoes=50;
    private int totalValores=0;
    private long tempoMedioQuick=0;
    private long tempoMedioSelection=0;
    
    public Principal(Desktop view){
        this.view = view;
        tabelaSelection = (DefaultTableModel) view.getTabelaSelection().getModel();
        tabelaQuick = (DefaultTableModel) view.getTabelaQuick().getModel();
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

            for(int repet=0; repet<interacoes; repet++){
                arrayOrdenado = selectionSort.SelectionSort(array);
                Object dados[]={repet+1, array.size(), selectionSort.getTimeSelection()};
                tabelaSelection.addRow(dados);

                quickSorte.QuickSort(array, 0, array.size()-1);
                Object dados2[]={repet+1, array.size(), selectionSort.getTimeSelection()};
                tabelaSelection.addRow(dados2);
            }

            obterMedia();
        }
        
    }
    
    private void obterMedia(){
        int linhasQuick = tabelaQuick.getRowCount();
        int linhaSelection = tabelaSelection.getRowCount();
        if(linhasQuick>0||linhaSelection>0){
            totalValores = array.size();
            
            
            BigDecimal timeQuick = new BigDecimal(0);
            BigDecimal timeSelection = new BigDecimal(0);
            String dadoTabelaQuick;
            String dadoTabelaSelection;
            
            
            for(int i=0; i<linhasQuick; i++){
                dadoTabelaQuick = tabelaQuick.getValueAt(i, 2).toString();
                timeQuick = timeQuick.add(new BigDecimal(dadoTabelaQuick));
            }
            timeQuick = timeQuick.divide(new BigDecimal(linhasQuick));
            tempoMedioQuick = timeQuick.longValueExact();
            
            for(int i=0; i<linhaSelection; i++){
                dadoTabelaSelection = tabelaSelection.getValueAt(i, 2).toString();
                timeSelection = timeSelection.add(new BigDecimal(dadoTabelaSelection));
            }
            timeSelection = timeSelection.divide(new BigDecimal(linhaSelection));
            tempoMedioSelection = timeSelection.longValueExact();
            
        }
    }
    
    public void gerarGraficos(){
        //Pega os dados da tabela e gera um gráfico em outra tela
    }
    
    public void exportarDados(){
        ExportDocuments exportar = new ExportDocuments();
        //Selection
        exportar.exportarExcel(view.getTabelaSelection().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", interacoes+"", totalValores+"", tempoMedioSelection+"");

        //Quick
        exportar.exportarExcel(view.getTabelaQuick().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", interacoes+"", totalValores+"", tempoMedioQuick+"");
    }
    
    public void salvarMedia(){
        String linhaSelection = interacoes+";"+totalValores+";"+tempoMedioSelection+";";
        String linhaQuick = interacoes+";"+totalValores+";"+tempoMedioQuick+";";
        try {
            FileWriter writerSelection = new FileWriter(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Selection.txt", true);
            FileWriter writerQuick = new FileWriter(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Quick.txt", true);
            
            writerSelection.write(linhaSelection);
            writerQuick.write(linhaQuick);
            
            writerQuick.close();
            writerSelection.close();

        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Não foi possível gravar a média", "Falha!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void exportarOrdenação(){
        if(arrayOrdenado!=null){
            ExportDocuments export = new ExportDocuments();
            export.geraArrayTxt(arrayOrdenado, System.getProperty("user.home")+
                    "/documents/Métodos de Ordenação/Números Ordenados/"+arrayOrdenado.size()+
                    "N"+gerarPastas.dataEHoraCodificada()+".txt");
        }
        
    }
}
