/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAONotificacion;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class ComandoCrearNotificacion extends ComandoGeneral{
    // Ver con el cliente creado
    public ArrayList ordenar(Object asistencia){
           System.out.println("ordenar " + asistencia);
           String asitenciaL = asistencia.toString();
           asitenciaL =asitenciaL.replace("{","");
           asitenciaL = asitenciaL.replace("}","");
           String str[] = asitenciaL.split(",");   
           ArrayList lista = new ArrayList();
           lista.add(0,str[0]);
           lista.add(1,str[1]);
           System.out.println("Lista posici[on 2 "+lista.get(1));
           return lista;
    } 
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAONotificacion _daoNotificacion;
           _daoNotificacion = FabricaDAO.ObtenerDAONotificacion();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOCrearNotifiacion");                                 
           ArrayList lista = ordenar(parametro);
           System.out.println("Lista "+ lista);
           boolean CrearNotifiacion = _daoNotificacion.CrearNotificacion(lista.toString());                           
           return CrearNotifiacion;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO crear notificacion");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo cargar la asistencia\"}");
             return o.toString();                      
        }
    }
    
}
