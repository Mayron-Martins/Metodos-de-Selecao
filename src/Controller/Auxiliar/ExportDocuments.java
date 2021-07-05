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
    public void exportarExcel(TableModel tabela, String caminho, String interacoes, String quantValores, String mediaTempos){
        JFileChooser excelExportChooser = new JFileChooser();
        excelExportChooser.setCurrentDirectory(new File(System.getProperty("user.home")+caminho));
        excelExportChooser.setDialogTitle("Salvar Aquivo do Excel");
        //filter the file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo do Excel", "*.xls", "xls", "xlsx", "xlsn");
        excelExportChooser.addChoosableFileFilter(filter);
        excelExportChooser.setFileFilter(filter);
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
                cell.setCellValue("Valores(*1000)");
                cell = row.createCell(2);
                cell.setCellValue("Média de Tempo");

                
                //Adicionar valores
                row = sheet.createRow(tabela.getRowCount()+3);
                cell = row.createCell(0);
                cell.setCellValue(interacoes);
                cell = row.createCell(1);
                cell.setCellValue(quantValores);
                cell = row.createCell(2);
                cell.setCellValue(mediaTempos);

                
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
			writer.newLine();
                        writer.flush();
                        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
