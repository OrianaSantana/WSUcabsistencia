/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.Horario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.MapUtil;

/**
 *
 * @author el_je_000
 */
public class DAOHorario extends DAOGeneral implements IDAOHorario{
    
    public ENTIDAD _horario = FABRICAENTIDAD.obtenerHorario(0, null, null, null, null, 0, 0, null, null, null);

    
    public enum NodeType implements Label{
        Horario;
    }
    /*private static String FindHorarioByProfesorID(String id_profesor){          
        System.out.println("FIND "+id_profesor);
        return "MATCH (Horario)-[:Pertenece_a]->(Usuario{Usu_id:"+id_profesor+"}) MATCH (Horario)-[:Se_dica_EN]->(Salon) RETURN Usuario, Horario, Salon";
    }*/
    private static String HorariosID(String id_profesor){
        System.out.println("HORARIOS ID");
        return "MATCH (:Usuario{Usu_id:"+id_profesor+"})<-[:Pertenece_a]-(Horario) RETURN Horario";
    }
    private static String HorariosCorreo(String correo_profesor){
        System.out.println("HORARIOS por correo");
        return "MATCH (:Usuario{Usu_correo:"+correo_profesor+"})<-[:Pertenece_a]-(Horario) RETURN Horario";
    }
   /* private static String FindHorarios(String id_horarios){
        System.out.println("FIND HORARIOS");
        return "MATCH (Horario {Hor_id:"+id_horarios+"}) RETURN Horario";        
    }  */
    
    @Override
    public ArrayList<Horario> horarios(String id_profesor) {
        
        ArrayList<Horario> ListaHorario = new ArrayList<Horario>();
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Hor_tipo", "horarios" ); 
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAOHorario");             
             Result resultfound = graphDb.execute( HorariosID(id_profesor), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO HORARIO Lista de horarios");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("Horario")).getProperty("Hor_id").toString();
                    int hor_id = Integer.parseInt(id);
                    String hor_dia = ((Node) row.get("Horario")).getProperty("Hor_dia").toString();
                    String hor_hora_inicio = ((Node) row.get("Horario")).getProperty("Hor_hora_inicio").toString();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");                    

                    String hor_hora_fin = ((Node) row.get("Horario")).getProperty("Hor_hora_fin").toString();
                    String hor_catedra = ((Node) row.get("Horario")).getProperty("Hor_catedra").toString();
                    String hor_nrc_catedra = ((Node) row.get("Horario")).getProperty("Hor_nrc_catedra").toString();                    
                    String hor_seccion = ((Node) row.get("Horario")).getProperty("Hor_seccion").toString();
                    String hor_salon = ((Node) row.get("Horario")).getProperty("Hor_salon").toString();
                    this._horario = FABRICAENTIDAD.obtenerHorario(hor_id, hor_dia, simpleDateFormat.parse(hor_hora_inicio), simpleDateFormat.parse(hor_hora_fin), hor_catedra, Integer.parseInt(hor_nrc_catedra),Integer.parseInt(hor_seccion), hor_salon, null, null);
                    ListaHorario.add((Horario) _horario);
                }            
             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOHorario, Función Consultar horario, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                } catch (ParseException ex) {        
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        
     
        System.out.println(ListaHorario); 
        
        //PRUEBA DE COMO AGARRAR LOS VALORES DE LA LISTA DESDE EL DAO
        
        /* String variable = "Esta es mi nueva lista";
      
         for (int i = 0; i < ListaHorario.size(); i++) {
         
            variable = variable + "\n" + ListaHorario.get(i).hor_dia + "," + ListaHorario.get(i).hor_catedra + "," + ListaHorario.get(i).hor_salon;
         }
         
        System.out.println("PequeListaHorario:" + variable);*/
        
        return ListaHorario;        
    }
    
    @Override
    public ArrayList<Horario> horariosXCorreo(String correo_profesor) {
        
        ArrayList<Horario> ListaHorario = new ArrayList<Horario>();
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Hor_tipo", "horarios" ); 
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAOHorario");
             String buscar_correo_profesor = "'"+correo_profesor+"'";
             Result resultfound = graphDb.execute( HorariosCorreo(buscar_correo_profesor), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO HORARIO Lista de horarios");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("Horario")).getProperty("Hor_id").toString();
                    int hor_id = Integer.parseInt(id);
                    String hor_dia = ((Node) row.get("Horario")).getProperty("Hor_dia").toString();
                    String hor_hora_inicio = ((Node) row.get("Horario")).getProperty("Hor_hora_inicio").toString();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");                    

                    String hor_hora_fin = ((Node) row.get("Horario")).getProperty("Hor_hora_fin").toString();
                    String hor_catedra = ((Node) row.get("Horario")).getProperty("Hor_catedra").toString();
                    String hor_nrc_catedra = ((Node) row.get("Horario")).getProperty("Hor_nrc_catedra").toString();                    
                    String hor_seccion = ((Node) row.get("Horario")).getProperty("Hor_seccion").toString();
                    String hor_salon = ((Node) row.get("Horario")).getProperty("Hor_salon").toString();
                    this._horario = FABRICAENTIDAD.obtenerHorario(hor_id, hor_dia, simpleDateFormat.parse(hor_hora_inicio), simpleDateFormat.parse(hor_hora_fin), hor_catedra, Integer.parseInt(hor_nrc_catedra),Integer.parseInt(hor_seccion), hor_salon, null, null);
                    ListaHorario.add((Horario) _horario);
                }            
             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAONotificacion, Función Consultar notificaciones, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                } catch (ParseException ex) {        
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        System.out.println(ListaHorario);        
        
        
        return ListaHorario;        
    }
    
/*    public Dominio.ENTIDAD ConsultarHorarioProfesor (String id_profesor) throws ParseException
    {
        System.out.println("ID "+id_profesor);
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Integer> listaIDHorarios = new ArrayList<Integer>();
        
        final Map<String, Object> params = MapUtil.map( "Hor_tipo", "horarios" );               
        try (Transaction tx = graphDb.beginTx()){
            
             System.out.println("TRY DAOHORARIO");
             
             Result resultfound = graphDb.execute( FindHorarioByProfesorID(id_profesor), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAOHORARIO");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("Horario")).getProperty("Hor_id").toString();
                    int hor_id = Integer.parseInt(id);
                    System.out.println(hor_id);
                    String hor_dia = ((Node) row.get("Horario")).getProperty("Hor_dia").toString();
                    System.out.println(hor_dia);
                    String hor_hora_inicio = ((Node) row.get("Horario")).getProperty("Hor_hora_inicio").toString();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");                    

                    System.out.println(hor_hora_inicio);
                    String hor_hora_fin = ((Node) row.get("Horario")).getProperty("Hor_hora_fin").toString();
                    System.out.println(hor_hora_fin);
                    String hor_catedra = ((Node) row.get("Horario")).getProperty("Hor_catedra").toString();
                    System.out.println(hor_catedra); 
                    String hor_nrc_catedra = ((Node) row.get("Horario")).getProperty("Hor_nrc_catedra").toString();                    
                    System.out.println(hor_nrc_catedra);
                    String hor_seccion = ((Node) row.get("Horario")).getProperty("Hor_seccion").toString();
                    System.out.println(hor_seccion);
                    String hor_tipo = ((Node) row.get("Horario")).getProperty("Hor_tipo").toString();                    
                    System.out.println(hor_tipo);                                                                   
                    listaIDHorarios = horarios(graphDb,id_profesor);
                    System.out.println(listaIDHorarios);
                    return this._horario = FABRICAENTIDAD.obtenerHorario(hor_id, hor_dia, simpleDateFormat.parse(hor_hora_inicio), simpleDateFormat.parse(hor_hora_fin), hor_catedra, Integer.parseInt(hor_nrc_catedra),Integer.parseInt(hor_seccion), null, null, null);
                    
                }
            tx.success(); 
            System.out.println("Termino TRY");
        }
         catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOHorario, Función ConsultarHorarioPorIDProfesor, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        catch(ParseException e)
        {
            System.out.println("Error en DAOHorario");
            throw e;
        }finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        return null;
    }
    public ArrayList horarios(GraphDatabaseService graphDb, String id_profesor) throws ParseException 
    {
        ArrayList<Integer> ListaID = new ArrayList<Integer>();
        final Map<String, Object> params = MapUtil.map( "Usu_tipo", "Profesor" );                               
        Result resultfound = graphDb.execute( HorariosID(id_profesor), params );             
        System.out.println(resultfound); 
        while (resultfound.hasNext())
            {      
                System.out.println("WHILE DAO Buscar Horarios");
                Map<String,Object> row = resultfound.next();
                System.out.println("NEXT");
                String id =((Node) row.get("Horario")).getProperty("Hor_id").toString();                    
                int hor_id = Integer.parseInt(id);
                ListaID.add(hor_id);
            }
        Collections.sort(ListaID);
        //ArrayList<Horario> listaHorario = new ArrayList<Horario>();
        ListaHorarios(graphDb, ListaID);
        return ListaID;
    }
    
    public ArrayList ListaHorarios(GraphDatabaseService graphDb, ArrayList<Integer> horarios) throws ParseException
    {
        ArrayList<Horario> listaHorario = new ArrayList<Horario>();
        for(int i = 0; i<= horarios.size()-1; i++)
        {   
            String id_horario = Integer.toString(horarios.get(i));
            System.out.println(horarios.get(i));
            final Map<String, Object> params = MapUtil.map( "Hor_tipo", "horarios" );               
            Result resultfound = graphDb.execute( FindHorarios(id_horario), params );             
            System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO HORARIO Lista de horarios");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("Horario")).getProperty("Hor_id").toString();
                    int hor_id = Integer.parseInt(id);
                    String hor_dia = ((Node) row.get("Horario")).getProperty("Hor_dia").toString();
                    String hor_hora_inicio = ((Node) row.get("Horario")).getProperty("Hor_hora_inicio").toString();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");                    

                    String hor_hora_fin = ((Node) row.get("Horario")).getProperty("Hor_hora_fin").toString();
                    String hor_catedra = ((Node) row.get("Horario")).getProperty("Hor_catedra").toString();
                    String hor_nrc_catedra = ((Node) row.get("Horario")).getProperty("Hor_nrc_catedra").toString();                    
                    String hor_seccion = ((Node) row.get("Horario")).getProperty("Hor_seccion").toString();
                    this._horario = FABRICAENTIDAD.obtenerHorario(hor_id, hor_dia, simpleDateFormat.parse(hor_hora_inicio), simpleDateFormat.parse(hor_hora_fin), hor_catedra, Integer.parseInt(hor_nrc_catedra),Integer.parseInt(hor_seccion), null, null, null);
                    listaHorario.add((Horario) _horario);                    
                }            
        }
        System.out.println(listaHorario);
        return listaHorario;
    }*/
}
