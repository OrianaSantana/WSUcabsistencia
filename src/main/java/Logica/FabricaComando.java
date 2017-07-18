/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Datos.DAOGeneral;
import Dominio.ENTIDAD;
import Dominio.Preferencia;
import java.util.ArrayList;

/**
 *
 * @author el_je_000
 */
public class FabricaComando {
    
    public static ComandoGeneral<String,String> ObtenerComandoProfesor() 
    {           
           return new ComandoConsultarProfesor();
    }
    public static ComandoGeneral<String,String> ObtenerComandoProfesorCorreo() 
    {           
           return new ComandoConsultarProfesorCorreo();
    }
    public static ComandoGeneral<String,String> ObtenerComandoHorarioProfesor() 
    {           
           return new ComandoConsultarHorarioProfesor();
    }
    public static ComandoGeneral<String,String> ObtenerComandoHorarioProfesorCorreo() 
    {           
           return new ComandoConsultarHorarioPorCorreo();
    }    
    public static ComandoGeneral<String,String> ObtenerComandoJustificacion() 
    {           
           return new ComandoConsultarJustificacion();
    }
    public static ComandoGeneral<String, String> ObtenerComandoNotificacion()
    {
           return new ComandoConsultarNotificacionProfesor();
    }
    public static ComandoGeneral<String, String> ObtenerComandoPreferencia()
    {
           return new ComandoConsultarPreferencia();
    }
    public static ComandoGeneral<String, String> ObtenerComandoModificarPreferencia()
    {
           return new ComandoModificarPreferencia();
    }   
    public static ComandoGeneral<String, String> ObtenerComandoCrearAsistencia()
    {
           return new ComandoCrearAsistenciaManual();
    }    
     public static ComandoGeneral<String, String> ObtenerComandoConstruirMapa()
    {
           return new ComandoConstruirMapa();
    }  
      public static ComandoGeneral<String, String> ObtenerComandoConstruirTest()
    {
           return new ComandoConstruirTest();
    }  
      public static ComandoGeneral<String, String> ObtenerComandoConsultarAsistencias()
    {
           return new ComandoConsultarAsistencias();
    }
}
