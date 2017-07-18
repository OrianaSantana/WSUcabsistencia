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
public class ComandoConsultarHorarioPorCorreo extends ComandoGeneral{

    @Override
    public Object Ejecutar (Object parametro)
    {
        try
        {
           IDAOHorario _daoHorario;
           _daoHorario = FabricaDAO.ObtenerDAOHorario();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOHorario por correo");
           //Horario horario =(Horario) _daoHorario.ConsultarHorarioProfesor(parametro.toString());
           ArrayList<Horario> listaHorarios = new  ArrayList<Horario>();
           listaHorarios = _daoHorario.horariosXCorreo(parametro.toString());
           System.out.println("JSON" + listaHorarios); 
           String horarios = listaHorarios.toString();
           horarios = horarios.replace("Horario", "");
           //return listaHorarios.toString();                      
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
