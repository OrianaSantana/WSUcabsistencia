/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOPreferencia;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
/**
 *
 * @author el_je_000
 */
public class ComandoModificarPreferencia extends ComandoGeneral{

    public ArrayList ordenar(Object preferencias){
           String preferenciasL = preferencias.toString();
           preferenciasL = preferenciasL.replace("[{","");
           preferenciasL = preferenciasL.replace("}]","");
           preferenciasL = preferenciasL.replace(",{","");
           String str[] = preferenciasL.split("}");   
           
           ArrayList lista = new ArrayList();
            lista.add(0,str[0]);
            lista.add(1,str[1]);
            lista.add(2,str[2]);
            //lista.add(3,str[3]);
            
           
           System.out.println("Lista  posici[on 2"+lista.get(2));
           
           return lista;
    }
    
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOPreferencia _daoPreferencia;
           _daoPreferencia = FabricaDAO.ObtenerDAOPreferecia();
           
           
           System.out.println(" LLAMADO A FABRICA OBTENER DAOPreferenciaModificar");                      
           boolean modificarListaPreferncias;
                      
           
           ArrayList lista = ordenar(parametro);
           
         
           modificarListaPreferncias = _daoPreferencia.ModificarPreferencia(lista.toString());
           //_daoPreferencia.ModificarPreferencia(preferencias);
           System.out.println("Respuesta  " + modificarListaPreferncias);                
           return modificarListaPreferncias;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO Modificar");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo Modificra la lista de preferencias\"}");
             return o.toString();                      
        }
    }
    
}
