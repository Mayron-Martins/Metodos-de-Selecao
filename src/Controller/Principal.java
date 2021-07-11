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
import View.Grafico;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final int interacoes = 50;
    private int totalValores = 0;
    private long tempoMedioQuick=0, tempoMedioSelection=0;
    private long desvioQuick=0, desvioSelection=0;
    
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
    
    public void gerarGraficos(){
        Grafico telaGraficos = new Grafico(view, false, tabelaSelection, tabelaQuick);
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
            ArrayList<Integer> arrayAuxiliar = new ArrayList<>();
            Quick_Sort quickSorte = new Quick_Sort();
            Selection_Sort selectionSort = new Selection_Sort();
            

            //Para o método Selection
            for(int repet=0; repet<interacoes; repet++){
                arrayAuxiliar.addAll(array);

                arrayOrdenado = selectionSort.SelectionSort(arrayAuxiliar);

                Object dados[]={repet+1, arrayAuxiliar.size(), selectionSort.getTimeSelection()};
                tabelaSelection.addRow(dados);
                
                arrayAuxiliar=new ArrayList<>();
            }
            long nanotime;
            //Para o método Quick
            for(int repet=0; repet<interacoes; repet++){
                arrayAuxiliar.addAll(array);
                
                nanotime = quickSorte.QuickSort(arrayAuxiliar, 0, arrayAuxiliar.size()-1);
                
                
                Object dados2[]={repet+1, arrayAuxiliar.size(), nanotime};
                tabelaQuick.addRow(dados2);
                
                arrayAuxiliar=new ArrayList<>();
            }

            obterMedia();
            view.gerarNumeros(true);
            JOptionPane.showMessageDialog(null, "Sucesso!");
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
            timeQuick = timeQuick.divide(new BigDecimal(linhasQuick),4, RoundingMode.UP);
            tempoMedioQuick = timeQuick.longValue();
            
            for(int i=0; i<linhaSelection; i++){
                dadoTabelaSelection = tabelaSelection.getValueAt(i, 2).toString();
                timeSelection = timeSelection.add(new BigDecimal(dadoTabelaSelection));
            }
            timeSelection = timeSelection.divide(new BigDecimal(linhaSelection),4, RoundingMode.UP);
            tempoMedioSelection = timeSelection.longValue();
            
            desvioSelection = desvioPadrao(tabelaSelection, timeSelection);
            desvioQuick = desvioPadrao(tabelaQuick, timeQuick);
        }
    }
    
    
    public void exportarDados(){
        ExportDocuments exportar = new ExportDocuments();
        if(view.getCheckSelection().isSelected()){
            //Selection
            exportar.exportarExcel("Salvar tabela Selection em ...", view.getTabelaSelection().getModel(), 
                    "/documents/Métodos de Ordenação/Dados Exportados", interacoes+"", totalValores+"", 
                    tempoMedioSelection+"", desvioSelection+"");

        }
        if(view.getCheckQuick().isSelected()){
            //Quick
            exportar.exportarExcel("Salvar tabela Quick em ...", view.getTabelaQuick().getModel(), 
                    "/documents/Métodos de Ordenação/Dados Exportados", interacoes+"", totalValores+"", 
                    tempoMedioQuick+"", desvioQuick+"");
    
        }
    }
    
    public void salvarMedia(){
        ArrayList linhaSelection = new ArrayList<>(), linhaQuick = new ArrayList<>();
        linhaSelection.add(interacoes+";"+totalValores+";"+tempoMedioSelection+";"+desvioSelection+";");
        linhaQuick.add(interacoes+";"+totalValores+";"+tempoMedioQuick+";"+desvioQuick+";");
        
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
    
    protected long desvioPadrao(DefaultTableModel tabela, BigDecimal media){
        int linhasTabela = tabela.getRowCount();
        long variancia=0,desvio=0;
        if(linhasTabela>0){
            BigDecimal time = new BigDecimal(0);
            BigDecimal diferenca;
            BigDecimal auxiliar;
            String dadoTabela="";
            for(int repet=0; repet<linhasTabela; repet++){
                try{
                    dadoTabela = tabela.getValueAt(repet, 2).toString();
                    diferenca= new BigDecimal(dadoTabela).subtract(media);
                    time = time.add((diferenca).pow(2));
                }catch(java.lang.ArithmeticException erro){};
            }
            
            int valores;
            if(linhasTabela==1){
                valores = linhasTabela;
            }
            else{
                valores = linhasTabela-1;
            }
            auxiliar = time.divide(new BigDecimal(valores), 4, BigDecimal.ROUND_UP);
            desvio = (long) Math.sqrt(auxiliar.doubleValue());
        }
        
        
        return desvio;
    }
}
