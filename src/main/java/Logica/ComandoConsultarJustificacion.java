/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOJustificacion;
import Datos.IDAOProfesor;
import Dominio.Justificacion;
import Dominio.Profesor;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class ComandoConsultarJustificacion extends ComandoGeneral {
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOJustificacion _daoJustificacion;
           _daoJustificacion = FabricaDAO.ObtenerDAOJustificacion();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOJustificacion");
           //Justificacion justificacion =(Justificacion) _daoJustificacion.ConsultarJustificacion();
           ArrayList<Justificacion> listaJustificaciones = new  ArrayList<Justificacion>();
           listaJustificaciones = _daoJustificacion.ConsultarJustificacion();
           System.out.println("JSON" + listaJustificaciones);
           String justificaciones = listaJustificaciones.toString();
           justificaciones = justificaciones.replace("Justificacion", "");           
          
           return justificaciones;
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO CONSULTAR");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo consultar la lista de justificaciones\"}");
             return o.toString();                      
        }
    }
    
    
}
