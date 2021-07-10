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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Mayro
 */
public class GraphicsGenerate {
    private JFreeChart chart;
    public XYDataset gerarGraficos(ArrayList <Long> array, String titulo){
        XYSeries pontos = pontos(array);
        XYDataset dataSetAreaXY = new XYSeriesCollection(pontos);
        
        JFreeChart grafico = graficoJFree(titulo, dataSetAreaXY);
        exibirGraficoEmFrame(grafico, titulo);
        
        this.chart = grafico;
        return dataSetAreaXY;
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
    
    private void exibirGraficoEmFrame(JFreeChart graficoAreaXY, String titulo){
        JFrame frame = new JFrame("Gráfico das Médias do "+titulo);
        frame.setPreferredSize(new Dimension(280, 290));
        ChartPanel painelDoGrafico = new ChartPanel(graficoAreaXY);
        painelDoGrafico.setPreferredSize(new Dimension(260, 280));
        frame.add(painelDoGrafico);
        frame.pack();
        frame.setLocation(10, 90);
        frame.setVisible(true);
    }
    
    /*
    public void diferencaGraficos(XYDataset graficoA, XYDataset graficoB){
        JFrame frame = new JFrame("Gráfico Comparativo dos Métodos");
        frame.setPreferredSize(new Dimension(280, 290));

        XYDifferenceRenderer renderer = new XYDifferenceRenderer();
        XYPlot plot = new XYPlot();
        plot.setDataset(0,graficoA);
        plot.setDataset(1,graficoB);
        plot.setRenderer(renderer);
        
        JFreeChart chart = new JFreeChart(plot);
        chart.removeLegend();
        
        ChartPanel painelDoGrafico = new ChartPanel(chart);
        painelDoGrafico.setPreferredSize(new Dimension(260, 280));
        frame.add(painelDoGrafico);
        frame.pack();
        frame.setLocation(10, 90);
        frame.setVisible(true);
    }*/
    
    public void criarArquivoJPEG(JFreeChart graficoAreaXY, String path, int largura, int altura){
        File arquivo = new File(path+".jpeg");
        try{
            ChartUtilities.saveChartAsPNG(arquivo, graficoAreaXY, altura, largura);
            Desktop.getDesktop().open(arquivo);
        }catch(IOException erro){
            JOptionPane.showMessageDialog(null, "Falha ao exportar Gráfico");
        }
    }

    public JFreeChart getChart() {
        return chart;
    }
    
    
}
