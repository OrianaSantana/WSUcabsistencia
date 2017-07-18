/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOHorario;
import Dominio.Horario;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class ComandoConsultarHorarioProfesor extends ComandoGeneral{
    
    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOHorario _daoHorario;
           _daoHorario = FabricaDAO.ObtenerDAOHorario();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOHorario");
           //Horario horario =(Horario) _daoHorario.ConsultarHorarioProfesor(parametro.toString());
           ArrayList<Horario> listaHorarios = new  ArrayList<Horario>();
           listaHorarios = _daoHorario.horarios(parametro.toString());
           System.out.println("JSON" + listaHorarios); 
           String horarios = listaHorarios.toString();
           horarios = horarios.replace("Horario", "");
           //return listaHorarios.toString();   
           
           
           //ESTO ES LO QUE HAY QUE HACER CON HUELLAS
         /*String variable = "Esta es mi nueva lista";
      
         for (int i = 0; i < listaHorarios.size(); i++) {
         
            variable = variable + "\n" + listaHorarios.get(i).hor_dia + "," + listaHorarios.get(i).hor_catedra + "," + listaHorarios.get(i).hor_salon;
         }
         
        System.out.println("PequeListaHorario:" + variable);*/
        
           return horarios;
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO CONSULTAR");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo consultar el horario\"}");
             return o.toString();                      
        }
    }
    
}
