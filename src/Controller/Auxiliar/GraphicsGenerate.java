/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Auxiliar;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Mayro
 */
public class GraphicsGenerate {
    public JFreeChart gerarGraficos(JPanel painel, ArrayList <Long> array, String titulo){
        XYSeries pontos = pontos(array);
        XYDataset dataSetAreaXY = new XYSeriesCollection(pontos);
        
        JFreeChart grafico = graficoJFree(titulo, dataSetAreaXY);
        exibirGraficoEmFrame(painel, grafico);
        
        return grafico;
    }
    
    private XYSeries pontos(ArrayList <Long> array){
        XYSeries pontosDoGrafico = new XYSeries("Tempo de processamento por execução");
        
        int ordem=1;
        for(long number : array){
            pontosDoGrafico.add(ordem, number);
            ordem++;
        }
        return pontosDoGrafico;
    }
    
    private JFreeChart graficoJFree(String titulo, XYDataset dataSetAreaXY){
        JFreeChart graficoAreaXY = ChartFactory.createXYAreaChart(
                titulo, 
                "Execuções", 
                "Tempo(ns)", 
                dataSetAreaXY,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        return graficoAreaXY;
    }
    
    private void exibirGraficoEmFrame(JPanel painel, JFreeChart graficoAreaXY){
        ChartPanel painelDoGrafico = new ChartPanel(graficoAreaXY);
        painelDoGrafico.setPreferredSize(new Dimension(painel.getHeight(), painel.getWidth()));
        painel.add(painelDoGrafico);
    }
    
    public void criarArquivoJPEG(JFreeChart graficoAreaXY, String path, int largura, int altura){
        File arquivo = new File(path+".jpeg");
        try{
            ChartUtilities.saveChartAsPNG(arquivo, graficoAreaXY, altura, largura);
            Desktop.getDesktop().open(arquivo);
        }catch(IOException erro){
            JOptionPane.showMessageDialog(null, "Falha ao exportar Gráfico");
        }
    }
}
