/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Huella;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public interface IDAOHuella extends IDAOGeneral{
    
    public String ModificarTest(String TestHuellas);
    public ArrayList<Huella> ConsultarHuellasMagneticas();
}
