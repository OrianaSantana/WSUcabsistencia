/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOProfesor;
import Dominio.Profesor;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author el_je_000
 */
public class ComandoConsultarProfesorCorreo extends ComandoGeneral{
    
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOProfesor _daoProfesor;
           _daoProfesor = FabricaDAO.ObtenerDAOProfesor();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOPROFESOR");
           Profesor profesor =(Profesor) _daoProfesor.ConsultarProfesorPorCorreo(parametro.toString());
           System.out.println("JSON" + profesor);     
           String profesorString = "";
           profesorString = profesor.toString();
           profesorString = profesorString.substring(8);
           
           return profesorString;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO CONSULTAR");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo consultar el profesor\"}");
             return o.toString();                      
        }
    }
    
}
