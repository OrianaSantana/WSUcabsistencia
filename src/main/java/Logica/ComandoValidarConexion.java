/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOProfesor;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class ComandoValidarConexion extends ComandoGeneral{
    public ArrayList ordenar(Object preferencias){
           String profesor = preferencias.toString();
           profesor = profesor.replace("{","");
           profesor = profesor.replace("}","");
           //profesor = profesor.replace(",{","");
           profesor = profesor.replace("Usu_id", "");
           profesor = profesor.replace("Usu_foto", "");
           profesor = profesor.replace("\"", "");
           profesor = profesor.replace(":", "");           
           String str[] = profesor.split(",");   
           ArrayList lista = new ArrayList();
           lista.add(0,str[0]);
           lista.add(1,str[1]);            
            System.out.println("Lista posici[on 0 "+lista.get(0));
           return lista;
    }
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOProfesor _daoProfesor;
           _daoProfesor = FabricaDAO.ObtenerDAOProfesor();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOProfesor");                      
           boolean modificarEstatusConexion;                      
           ArrayList lista = ordenar(parametro);
           modificarEstatusConexion = _daoProfesor.ModificarStatusConexion(lista.toString());
           System.out.println("Respuesta  " + modificarEstatusConexion);                
           System.out.println("Respuesta  " + lista);                
           return modificarEstatusConexion;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO Modificar");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo Modificra el status de la conexion\"}");
             return o.toString();                      
        }
    }
    
}
