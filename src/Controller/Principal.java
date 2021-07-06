/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Auxiliar.ExportDocuments;
import Controller.Auxiliar.FilesGenerate;
import Controller.Auxiliar.GenerateNumbers;
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
    private final ExportDocuments export = new ExportDocuments();
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
        String campo = view.getCampoQuantidade().getText();
        if(!campo.trim().equals("")&&campo!=null){
            int quantidade = Integer.parseInt(campo)*1000;
            if(quantidade!=0){
                GenerateNumbers generate = new GenerateNumbers();
                array = generate.gerarNumeros(quantidade);
                view.gerarNumeros(false);
            }
            
        }
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
            
            //Para o método Selection
            for(int repet=0; repet<interacoes; repet++){
                arrayOrdenado = selectionSort.SelectionSort(array);
                Object dados[]={repet+1, array.size(), selectionSort.getTimeSelection()};
                tabelaSelection.addRow(dados);
            }
            
            //Para o método Quick
            for(int repet=0; repet<interacoes; repet++){
                quickSorte.QuickSort(array, 0, array.size()-1);
                Object dados2[]={repet+1, array.size(), quickSorte.getTimeQuick()};
                tabelaQuick.addRow(dados2);
            }

            obterMedia();
            view.gerarNumeros(true);
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
        if(view.getCheckSelection().isSelected()){
            //Selection
            exportar.exportarExcel("Salvar tabela Selection em ...", view.getTabelaSelection().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", interacoes+"", totalValores+"", tempoMedioSelection+"");

        }
        if(view.getCheckQuick().isSelected()){
            //Quick
            exportar.exportarExcel("Salvar tabela Quick em ...", view.getTabelaQuick().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", interacoes+"", totalValores+"", tempoMedioQuick+"");
    
        }
    }
    
    public void salvarMedia(){
        ArrayList linhaSelection = new ArrayList<>(), linhaQuick = new ArrayList<>();
        linhaSelection.add(interacoes+";"+totalValores+";"+tempoMedioSelection+";");
        linhaQuick.add(interacoes+";"+totalValores+";"+tempoMedioQuick+";");
        
        export.geraArrayTxt(linhaSelection, System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Selection.txt");
        export.geraArrayTxt(linhaQuick, System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Quick.txt");
    }
    
    public void exportarOrdenação(){
        if(arrayOrdenado!=null){
            export.geraArrayTxt(arrayOrdenado, System.getProperty("user.home")+
                    "/documents/Métodos de Ordenação/Números Ordenados/"+arrayOrdenado.size()+
                    "N"+gerarPastas.dataEHoraCodificada()+".txt");
            
        }
    }
}
