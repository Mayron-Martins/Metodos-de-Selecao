/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Paineis;

import Controller.Auxiliar.ExportDocuments;
import Controller.Principal;
import View.Desktop;
import View.Grafico;
import View.Medias;
import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            timeQuick = timeQuick.divide(new BigDecimal(linhasQuick), 4, RoundingMode.UP);
            
            for(int i=0; i<linhaSelection; i++){
                dadoTabelaSelection = tabelaMediaSelection.getValueAt(i, 2).toString();
                timeSelection = timeSelection.add(new BigDecimal(dadoTabelaSelection));
                
                dadoTabelaSelection = tabelaMediaSelection.getValueAt(i, 1).toString();
                quantValoresSelection+=Integer.parseInt(dadoTabelaSelection);
            }
            timeSelection = timeSelection.divide(new BigDecimal(linhaSelection), 4, RoundingMode.UP);
            
            long desvioSelection = desvioPadrao(tabelaMediaSelection, timeSelection);
            long desvioQuick = desvioPadrao(tabelaMediaQuick, timeQuick);

            ExportDocuments exportar = new ExportDocuments();
            //Selection
            exportar.exportarExcel("Salvar tabela de M??dias Selection em ...", view2.getTabelaSelection().getModel(), 
                    "/documents/M??todos de Ordena????o/Dados Exportados", ""+interacoesSelection, ""+quantValoresSelection, 
                    timeSelection.longValue()+"", desvioSelection+"");

            //Quick
            exportar.exportarExcel("Salvar tabela de M??dias Quick em ...", view2.getTabelaQuick().getModel(), 
                    "/documents/M??todos de Ordena????o/Dados Exportados", ""+interacoesQuick, ""+quantValoresQuick, 
                    timeQuick.longValue()+"", desvioQuick+"");
        }
    }
    
    public void visualizarMedias(){
        limparTabelas();
        inserirMediasTabelas(System.getProperty("user.home")+"/documents/M??todos de Ordena????o/Medias Selection.txt", false);
        inserirMediasTabelas(System.getProperty("user.home")+"/documents/M??todos de Ordena????o/Medias Quick.txt", true);
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
            String desvio = "";
            
            for(String media : medias){
                quantNumeros = media.split(";")[1].replace(";", "");
                time = media.split(";")[2].replace(";", "");
                desvio = media.split(";")[3].replace(";", "");
                
                Object dados[] = {interacao, quantNumeros, time, desvio};
                
                if(quick){
                    tabelaMediaQuick.addRow(dados);
                }else{
                    tabelaMediaSelection.addRow(dados);
                }
                interacao++;
            }
        }
    }
    
    @Override
    public void gerarGraficos(){
        Grafico telaGraficos = new Grafico(view, false, tabelaMediaSelection, tabelaMediaQuick);
    }
    
}
