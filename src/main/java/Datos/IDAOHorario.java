/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Horario;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public interface IDAOHorario extends IDAOGeneral{
    
    /*
     * Funcion que consulta un profesor segun un id
     * @param IDProfesor formato String
     * @return Entidad de tipo Profesor
     */        
    //public Dominio.ENTIDAD ConsultarHorarioProfesor (String id_profesor) throws ParseException;
    
    public ArrayList<Horario> horarios(String id_profesor);
    
    public ArrayList<Horario> horariosXCorreo(String correo_profesor);
}
