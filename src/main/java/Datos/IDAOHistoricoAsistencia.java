/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.HistoricoAsistencia;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public interface IDAOHistoricoAsistencia extends IDAOGeneral{

     /*
     * Funcion que carga la asistencia manual.
     * @param  formato String
     * @return Entidad de tipo asistencia
     */      
public boolean CrearAsistenciaManual(String asistencias);        

public ArrayList<HistoricoAsistencia> asistencias(String toString);
}
