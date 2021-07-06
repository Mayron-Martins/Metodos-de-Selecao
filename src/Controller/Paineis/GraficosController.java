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
import java.util.ArrayList;
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
    
    public GraficosController(Desktop view, Grafico view2) {
        super(view);
        this.view2 = view2;
        gerarGraficos();
    }
    

    public final void gerarGraficos(DefaultTableModel tabelaSelection, DefaultTableModel tabelaQuick){
        System.out.println("Chegou aqui");
        temposSelection = tempoTabelas(tabelaSelection);
        temposQuick = tempoTabelas(tabelaQuick);
        
        view2.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        
        if(temposSelection!=null){
            graphicSelection = graficos.gerarGraficos(view2.getPainelSelection(), temposSelection, "Método Selection Sort");
        }
        if(temposQuick!=null){
            graphicsQuick = graficos.gerarGraficos(view2.getPainelSelection(), temposQuick, "Método Quick Sort");
        }
        view2.setModal(true);
        view2.setVisible(true);
    }
    
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
            graficos.gerarGraficos(view2.getPainelSelection(), temposSelection, 
                    System.getProperty("user.home")+"/documents/Métodos de Ordenação/Dados Exportados/Selection Sort");
        }
        if(op==2){
            graficos.gerarGraficos(view2.getPainelQuick(), temposQuick, 
                    System.getProperty("user.home")+"/documents/Métodos de Ordenação/Dados Exportados/Quick Sort");
        }
    }
}
