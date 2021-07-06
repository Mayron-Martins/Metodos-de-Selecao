/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Paineis;

import Controller.Auxiliar.GraphicsGenerate;
import Controller.Principal;
import View.Desktop;
import View.Grafico;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Mayro
 */
public class GraficosController extends Principal{
    private final Grafico view2;
    private final GraphicsGenerate graficos = new GraphicsGenerate();
    private ArrayList<Long> temposSelection, temposQuick;
    private JFreeChart graphicSelection, graphicsQuick;
    
    public GraficosController(Desktop view, Grafico view2, DefaultTableModel selection, DefaultTableModel quick) {
        super(view);
        this.view2 = view2;
        this.gerarGraficos(selection, quick);
    }
    

    private void gerarGraficos(DefaultTableModel tabelaSelection, DefaultTableModel tabelaQuick){
        temposSelection = tempoTabelas(tabelaSelection);
        temposQuick = tempoTabelas(tabelaQuick);
        
        view2.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        
        if(temposSelection!=null){
            graphicSelection = graficos.gerarGraficos(view2, temposSelection, "Método Selection Sort");
        }
        if(temposQuick!=null){
            graphicsQuick = graficos.gerarGraficos(view2, temposQuick, "Método Quick Sort");
        }
        view2.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        view2.setVisible(true);
    }
    /*
    public void alterarlocalização(boolean abrindo, Window window){
        Point locationView = window.getLocation();
        Dimension sizeView = window.getSize();
        Dimension sizeView2 = view2.getSize();
        
        if(abrindo){
            window.setLocation(locationView.x-30, locationView.y);
            view2.setLocation(locationView.x+sizeView.width+10, locationView.y);
           
        }else{
            window.setLocation(locationView.x+30, locationView.y);
            view2.setLocation(locationView.x-sizeView.width-10, locationView.y);
            
        }
        
    }*/
    
    private ArrayList<Long> tempoTabelas(DefaultTableModel tabela){
        int linhas = tabela.getRowCount();
        ArrayList<Long> tempos = new ArrayList<>();
        
        if(linhas>0){
            
            String dadoTabela;
            
            for(int i=0; i<linhas; i++){
                dadoTabela = tabela.getValueAt(i, 2).toString();
                tempos.add(Long.parseLong(dadoTabela));
            }
        }
        
        return tempos;
    }
    
    public void exportarGraficos(int op){
        if(op==1){
            graficos.criarArquivoJPEG(graphicSelection, 
                    System.getProperty("user.home")+"/documents/Métodos de Ordenação/Dados Exportados/Selection Sort",
                    1980, 1080);
        }
        if(op==2){
            graficos.criarArquivoJPEG(graphicsQuick, 
                    System.getProperty("user.home")+"/documents/Métodos de Ordenação/Dados Exportados/Quick Sort",
                    1980, 1080);
        }
    }
    
    public void fechar(){
        view2.dispose();
    }
}
