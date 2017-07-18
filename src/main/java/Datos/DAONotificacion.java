/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.Justificacion;
import Dominio.Notificacion;
import java.util.ArrayList;
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
public class DAONotificacion extends DAOGeneral implements IDAONotificacion{
    
    public ENTIDAD _notificacion = FABRICAENTIDAD.obtenerNotificacion(0, null);

    public enum NodeType implements Label{
        Notificacion;
    }
    private static String FindNotificacion(String id_profesor){    
        System.out.println("FIND "+id_profesor);
        return "Match (n: Notificacion)-[Se_notifica_a]->(Usuario {Usu_id:"+id_profesor+"}) Return n";
    }
        
    @Override
    public ArrayList<Notificacion> ConsultarNotificacion(String id_profesor){
        ArrayList<Notificacion> ListaNotificacion = new ArrayList<Notificacion>();
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Not_tipo", "notificaciones" ); 
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAONotificacion");             
             Result resultfound = graphDb.execute( FindNotificacion(id_profesor), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO Notificacion Lista de notificaciones");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("n")).getProperty("Not_id").toString();
                    int not_id = Integer.parseInt(id);
                    String not_mensaje = ((Node) row.get("n")).getProperty("Not_mensaje").toString();
                    this._notificacion = FABRICAENTIDAD.obtenerNotificacion(not_id, not_mensaje);
                    ListaNotificacion.add((Notificacion) _notificacion);                    
                }            
             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAONotificacion, Función Consultar notificaciones, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        System.out.println(ListaNotificacion);        
        
        
        return ListaNotificacion;

    }
}
