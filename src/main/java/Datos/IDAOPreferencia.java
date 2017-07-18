/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Preferencia;
import java.util.ArrayList;
/**
 *
 * @author el_je_000
 */
public interface IDAOPreferencia extends IDAOGeneral{
        /*
     * Funcion que consulta lista de Justificaciones
     * @param  formato String
     * @return Entidad de tipo justificacion
     */   
    public ArrayList<Preferencia> ConsultarPreferencia(String id_profesor);
    
     /*
     * Funcion que consulta lista de Preferencias
     * @param  formato String
     * @return Entidad de tipo Preferencia
     */   
    public boolean ModificarPreferencia(String preferencia);

}
