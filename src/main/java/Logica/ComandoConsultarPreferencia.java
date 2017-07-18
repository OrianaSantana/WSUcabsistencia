/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOPreferencia;
import Dominio.Preferencia;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
/**
 *
 * @author el_je_000
 */
public class ComandoConsultarPreferencia extends ComandoGeneral{
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOPreferencia _daoPreferencia;
           _daoPreferencia = FabricaDAO.ObtenerDAOPreferecia();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOPreferencia");           
           ArrayList<Preferencia> listaPreferencia = new  ArrayList<Preferencia>();
           listaPreferencia = _daoPreferencia.ConsultarPreferencia(parametro.toString());
           System.out.println("JSON" + listaPreferencia);
           String preferencias = listaPreferencia.toString();
           preferencias = preferencias.replace("Preferencia", "");           
           return preferencias;                      
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
