/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOHistoricoAsistencia;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class ComandoCrearAsistenciaManual extends ComandoGeneral{
    
    public ArrayList ordenar(Object asistencia){
           System.out.println("ordenar " + asistencia);
           String asitenciaL = asistencia.toString();
           asitenciaL =asitenciaL.replace("{","");
           asitenciaL = asitenciaL.replace("}","");
           String str[] = asitenciaL.split(",");   
           ArrayList lista = new ArrayList();
           lista.add(0,str[0]);
           lista.add(1,str[1]);
           lista.add(2,str[2]);
           lista.add(3,str[3]);
           lista.add(4,str[4]);
           lista.add(5,str[5]);
           System.out.println("Lista posici[on 2 "+lista.get(3));
           return lista;
    }    
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOHistoricoAsistencia _daoHistoricoAsistencia;
           _daoHistoricoAsistencia = FabricaDAO.ObtenerDAOHistoricoAsistencia();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOCrearAsistencia");                                 
           ArrayList lista = ordenar(parametro);
           System.out.println("Lista "+ lista);
           boolean CrearAsistenciaManual = _daoHistoricoAsistencia.CrearAsistenciaManual(lista.toString());                           
           return CrearAsistenciaManual;                      
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO crear asistencia");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo cargar la asistencia\"}");
             return o.toString();                      
        }
    }
    
}
