/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Auxiliar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mayro
 */
public class ExportDocuments {
    public void exportarExcel(String titulo, TableModel tabela, String caminho, String interacoes, String quantValores, String mediaTempos, String desvio){
        JFileChooser excelExportChooser = new JFileChooser();
        excelExportChooser.setCurrentDirectory(new File(System.getProperty("user.home")+caminho));
        excelExportChooser.setDialogTitle("Salvar Aquivo do Excel");
        //filter the file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo do Excel", "*.xls", "xls", "xlsx", "xlsn");
        excelExportChooser.addChoosableFileFilter(filter);
        excelExportChooser.setFileFilter(filter);
        excelExportChooser.setDialogTitle(titulo);
        int excelchooser = excelExportChooser.showSaveDialog(null);

        if (excelchooser == JFileChooser.APPROVE_OPTION) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("SalesReturnsDetails");

            Row row;
            Cell cell;

            try {
                // write the column headers
                row = sheet.createRow(0);
                for (int colunas = 0; colunas < tabela.getColumnCount(); colunas++) {
                    cell = row.createCell(colunas);
                    cell.setCellValue(tabela.getColumnName(colunas));
                }

                for (int linhas = 0; linhas < tabela.getRowCount(); linhas++) {

                    row = sheet.createRow(linhas + 1);

                    for (int colunas = 0; colunas < tabela.getColumnCount(); colunas++) {
                        cell = row.createCell(colunas);
                        
                        //Verificar se a célula é nula
                        if(tabela.getValueAt(linhas, colunas)!=null){
                           cell.setCellValue(tabela.getValueAt(linhas, colunas).toString());
                        }
                        else{
                           cell.setCellValue(""); 
                        }

                    }

                }
                
                //Adicionar uma Coluna Vazia
                row = sheet.createRow(tabela.getRowCount()+1);
                for (int colunas = 0; colunas < tabela.getColumnCount(); colunas++) {
                        cell = row.createCell(colunas);
                        cell.setCellValue(""); 
                }
                
                //Adicionar Títulos
                row = sheet.createRow(tabela.getRowCount()+2);
                cell = row.createCell(0);
                cell.setCellValue("Interações");
                cell = row.createCell(1);
                cell.setCellValue("Valores");
                cell = row.createCell(2);
                cell.setCellValue("Média de Tempo(ns)");
                cell = row.createCell(3);
                cell.setCellValue("Desvio Padrão(ns)");

                
                //Adicionar valores
                row = sheet.createRow(tabela.getRowCount()+3);
                cell = row.createCell(0);
                cell.setCellValue(interacoes);
                cell = row.createCell(1);
                cell.setCellValue(quantValores);
                cell = row.createCell(2);
                cell.setCellValue(mediaTempos);
                cell = row.createCell(3);
                cell.setCellValue(desvio);

                
               FileOutputStream excelFIS = new FileOutputStream(excelExportChooser.getSelectedFile() + ".xlsx");
               workbook.write(excelFIS);
               workbook.close();
               excelFIS.close();
                
               JOptionPane.showMessageDialog(null, "Exportado com Sucesso!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
     }
    
    public void geraArquivoTxt(String conteudo, String caminho) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true));  
			writer.write(conteudo);
                        writer.flush();
                        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void geraArrayTxt(ArrayList <?> conteudo, String caminho) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true));  
                        for(Object numero : conteudo){
                            writer.write(""+numero);
                            writer.newLine();
                            writer.flush();
                        }
			
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Exportado com Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
