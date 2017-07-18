/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOHistoricoAsistencia;
import Dominio.HistoricoAsistencia;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author Oriana
 */
public class ComandoConsultarAsistencias extends ComandoGeneral{
    
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
            IDAOHistoricoAsistencia _daoHistoricoAsistencia;
           _daoHistoricoAsistencia = FabricaDAO.ObtenerDAOHistoricoAsistencia();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOAsistencia");
           ArrayList<HistoricoAsistencia> listaAsistencias = new  ArrayList<>();
           
          listaAsistencias = _daoHistoricoAsistencia.asistencias(parametro.toString());
           System.out.println("JSON" + listaAsistencias); 
          String asistencias = listaAsistencias.toString();
          asistencias = asistencias.replace("Historico Asistencia", "");  
          
           return asistencias;
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO CONSULTAR asistencias");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo consultar la lista de asistencias\"}");
             return o.toString();                      
        }
    }
}
