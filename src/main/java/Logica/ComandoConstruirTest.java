/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOHuella;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author Oriana
 */
public class ComandoConstruirTest extends ComandoGeneral{

    
     public ArrayList ordenar(Object huellasTest){
           String huellaT = huellasTest.toString();
           huellaT = huellaT.replace("[{","");
           huellaT = huellaT.replace("}]","");
           huellaT = huellaT.replace(",{","");
           String str[] = huellaT.split("}");   
           
           ArrayList listaTest = new ArrayList();
           
           for (int i = 0; i < str.length; i++) { 
           
            listaTest.add(i,str[i]);
           
           }
           
           System.out.println("Lista  posici[on 2"+listaTest.get(2));
           
           return listaTest;
    }
    
    @Override
    public Object Ejecutar(Object parametro) {
       try
        {
           IDAOHuella _daoHuella;
           _daoHuella = FabricaDAO.ObtenerDAOHuella();
           
           
           System.out.println(" LLAMADO A FABRICA OBTENER DAOHuellaTest");                      
          
           String SetDePrueba;
                    
           ArrayList listaTestNueva = ordenar(parametro);
           
           SetDePrueba = _daoHuella.ModificarTest(listaTestNueva.toString());
           //_daoPreferencia.ModificarPreferencia(preferencias);
           System.out.println("Respuesta  " + SetDePrueba);                
           return SetDePrueba;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO Modificar Test");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo Modificar el test a predecir\"}");
             return o.toString();                      
        }
    }
    
}
