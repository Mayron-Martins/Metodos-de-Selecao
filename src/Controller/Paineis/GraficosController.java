/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Paineis;

import Controller.Principal;
import View.Desktop;
import View.Grafico;

/**
 *
 * @author Mayro
 */
public class GraficosController extends Principal{
    private final Grafico view2;
    
    public GraficosController(Desktop view, Grafico view2) {
        super(view);
        this.view2 = view2;
    }
    
}
