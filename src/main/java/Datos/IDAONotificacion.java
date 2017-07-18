/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Notificacion;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public interface IDAONotificacion extends IDAOGeneral {
    /*
     * Funcion que consulta lista de Justificaciones
     * @param  formato String
     * @return Entidad de tipo justificacion
     */   
    public ArrayList<Notificacion> ConsultarNotificacion(String id_profesor);
    
    
}
