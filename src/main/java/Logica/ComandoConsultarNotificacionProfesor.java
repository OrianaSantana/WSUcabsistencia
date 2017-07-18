/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAONotificacion;
import Dominio.Notificacion;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class ComandoConsultarNotificacionProfesor extends ComandoGeneral{
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAONotificacion _daoNotificacion;
           _daoNotificacion = FabricaDAO.ObtenerDAONotificacion();
           System.out.println(" LLAMADO A FABRICA OBTENER DAONotificacion");
           ArrayList<Notificacion> listaNotificaciones = new  ArrayList<Notificacion>();
           listaNotificaciones = _daoNotificacion.ConsultarNotificacion(parametro.toString());
           System.out.println("JSON" + listaNotificaciones);      
           String notificaciones = listaNotificaciones.toString();
           notificaciones = notificaciones.replace("Notificacion", "");  
           return notificaciones;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO CONSULTAR");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo consultar la lista de notificaciones\"}");
             return o.toString();                      
        }
    }    
    
}
