/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import java.text.ParseException;

/**
 *
 * @author el_je_000
 */
public interface IDAOProfesor extends IDAOGeneral{
    /*
     * Funcion que consulta un profesor segun un id
     * @param IDProfesor formato String
     * @return Entidad de tipo Profesor
     */        
    public Dominio.ENTIDAD ConsultarProfesorPorID (String id_profesor) throws ParseException;    
    /*
     * Funcion que consulta un profesor segun un id
     * @param CorreoProfesor formato String
     * @return Entidad de tipo Profesor
     */        
    public Dominio.ENTIDAD ConsultarProfesorPorCorreo (String correo_profesor) throws ParseException;
     /*
     * Funcion que consulta lista de Preferencias
     * @param  formato String
     * @return Entidad de tipo Preferencia
     */   
    public boolean ModificarStatusConexion(String profesor);
     /*
     * Funcion que consulta lista de Preferencias
     * @param  formato String
     * @return Entidad de tipo Preferencia
     */    
    public boolean CerrarSesion(String profesor);
    
}
