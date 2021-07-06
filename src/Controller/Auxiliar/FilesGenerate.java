/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Auxiliar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Mayro
 */
public class FilesGenerate {
    public void gerarPastas(){
        File pastaPai = new File(System.getProperty("user.home")+"/documents/Métodos de Ordenação");
        File geracao = new File(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Números Gerados");
        File ordenacao = new File(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Números Ordenados");
        File tabelas = new File(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Dados Exportados");
        
        if(!pastaPai.exists()){pastaPai.mkdirs();}
        if(!geracao.exists()){geracao.mkdirs();}
        if(!ordenacao.exists()){ordenacao.mkdirs();}
        if(!tabelas.exists()){tabelas.mkdirs();}
        
        File mediasSelection = new File(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Selection.txt");
        if(!mediasSelection.exists()){
            ExportDocuments gerarTxt = new ExportDocuments();
            gerarTxt.geraArquivoTxt("", mediasSelection.getPath());
        }
        
        File mediasQuick = new File(System.getProperty("user.home")+"/documents/Métodos de Ordenação/Medias Quick.txt");
        if(!mediasQuick.exists()){
            ExportDocuments gerarTxt = new ExportDocuments();
            gerarTxt.geraArquivoTxt("", mediasQuick.getPath());
        }
    }
    
    public Long dataEHoraCodificada(){
        LocalDateTime dataAtual = LocalDateTime.now();
        Date data = Date.from(dataAtual.atZone(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = format.format(data);
        dataFormatada = dataFormatada.replaceAll("/", "");
        dataFormatada = dataFormatada.replaceAll(":", "");
        dataFormatada = dataFormatada.replaceAll(" ", "");
        return Long.valueOf(dataFormatada);
    }
}
