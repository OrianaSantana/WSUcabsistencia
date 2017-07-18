/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author el_je_000
 */
public class FabricaDAO {
    public static IDAOHistoricoAsistencia ObtenerDAOHistoricoAsistencia()
    {
        return new DAOHistoricoAsistencia();
    }
    
    public static IDAOHorario ObtenerDAOHorario()
    {
        return new DAOHorario();
    }
    
    public static IDAOHuella ObtenerDAOHuella()
    {
        return new DAOHuella();
    }
    
    public static IDAOJustificacion ObtenerDAOJustificacion()
    {
        return new DAOJustificacion();
    }
    
    public static IDAONotificacion ObtenerDAONotificacion()
    {
        return new DAONotificacion();
    }
    
    public static IDAOPreferencia ObtenerDAOPreferecia()
    {
        return new DAOPreferencia();
    }
    
    public static IDAOProfesor ObtenerDAOProfesor()
    {
        return new DAOProfesor();
    }
    
    public static IDAORol ObtenerDAORol()
    {
        return new DAORol();
    }
    
    public static IDAOSalon ObtenerDAOSalon()
    {
        return new DAOSalon();
    }
    
}
