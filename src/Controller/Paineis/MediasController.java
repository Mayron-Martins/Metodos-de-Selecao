/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Paineis;

import Controller.Auxiliar.ExportDocuments;
import Controller.Principal;
import View.Desktop;
import View.Medias;
import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mayro
 */
public class MediasController extends Principal{
    private final Medias view2;
    private final DefaultTableModel tabelaMediaSelection;
    private final DefaultTableModel tabelaMediaQuick;
    
    public MediasController(Desktop view, Medias view2) {
        super(view);
        tabelaMediaSelection = (DefaultTableModel) view2.getTabelaSelection().getModel();
        tabelaMediaQuick = (DefaultTableModel) view2.getTabelaQuick().getModel();
        this.view2 = view2;
    }

    @Override
    public void limparTabelas() {
        int quantLinhas = view2.getTabelaQuick().getRowCount();
        for(int i=0; i<quantLinhas; i++){
            tabelaMediaQuick.removeRow(0);
        }
        quantLinhas = view2.getTabelaSelection().getRowCount();
        for(int i=0; i<quantLinhas; i++){
            tabelaMediaSelection.removeRow(0);
        }
    }

    @Override
    public void exportarDados() {
        int linhasQuick = tabelaMediaQuick.getRowCount();
        int linhaSelection = tabelaMediaSelection.getRowCount();
        if(linhasQuick>0||linhaSelection>0){
            int interacoesQuick=linhasQuick*50;
            int interacoesSelection=linhaSelection*50;
            int quantValoresQuick=0;
            int quantValoresSelection=0;
            
            
            BigDecimal timeQuick = new BigDecimal(0);
            BigDecimal timeSelection = new BigDecimal(0);
            String dadoTabelaQuick;
            String dadoTabelaSelection;
            
            
            for(int i=0; i<linhasQuick; i++){
                dadoTabelaQuick = tabelaMediaQuick.getValueAt(i, 2).toString();
                timeQuick = timeQuick.add(new BigDecimal(dadoTabelaQuick));
                
                dadoTabelaQuick = tabelaMediaQuick.getValueAt(i, 1).toString();
                quantValoresQuick+=Integer.parseInt(dadoTabelaQuick);
            }
            timeQuick = timeQuick.divide(new BigDecimal(linhasQuick));
            
            for(int i=0; i<linhaSelection; i++){
                dadoTabelaSelection = tabelaMediaSelection.getValueAt(i, 2).toString();
                timeSelection = timeSelection.add(new BigDecimal(dadoTabelaSelection));
                
                dadoTabelaSelection = tabelaMediaSelection.getValueAt(i, 1).toString();
                quantValoresSelection+=Integer.parseInt(dadoTabelaSelection);
            }
            timeSelection = timeSelection.divide(new BigDecimal(linhaSelection));

            ExportDocuments exportar = new ExportDocuments();
            //Selection
            exportar.exportarExcel("Salvar tabela de Médias Selection em ...", view2.getTabelaSelection().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", ""+interacoesSelection, ""+quantValoresSelection, timeSelection.toString());

            //Quick
            exportar.exportarExcel("Salvar tabela de Médias Quick em ...", view2.getTabelaQuick().getModel(), "/documents/Métodos de Ordenação/Dados Exportados", ""+interacoesQuick, ""+quantValoresQuick, timeQuick.toString());
        }
    }
    
    public void visualizarMedias(){
        limparTabelas();
        inserirMediasTabelas(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Selection.txt", false);
        inserirMediasTabelas(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Quick.txt", true);
        
        view2.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        view2.setVisible(true);
    }
    
    private void inserirMediasTabelas(String path, boolean quick){
        ArrayList<String> medias = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader (
                new FileInputStream(path)
                )
            );

            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                medias.add(line);
            }   
            reader.close();  

        } catch (IOException e) {
            System.out.println("Erro ao ler aquivo!");

        }
        
        if(medias!=null){
            int interacao=1;
            String quantNumeros="";
            String time="";
            
            for(String media : medias){
                quantNumeros = media.split(";")[1].replace(";", "");
                time = media.split(";")[2].replace(";", "");
                
                Object dados[] = {interacao, quantNumeros, time};
                
                if(quick){
                    tabelaMediaQuick.addRow(dados);
                }else{
                    tabelaMediaSelection.addRow(dados);
                }
                interacao++;
            }
        }
    }
    
}
