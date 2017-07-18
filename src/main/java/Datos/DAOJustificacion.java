/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.Horario;
import Dominio.Justificacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class DAOJustificacion extends DAOGeneral implements IDAOJustificacion{

    public ENTIDAD _justificacion = FABRICAENTIDAD.obtenerJustificacion(0, null);
    
        public enum NodeType implements Label{
        Justificacion;
    }
    private static String FindJustificacion(){    
        System.out.println("FIND ");
        return "MATCH (n:Justificacion) RETURN n";
    }
    
    @Override
    public ArrayList<Justificacion> ConsultarJustificacion(){
        ArrayList<Justificacion> ListaJustificacion = new ArrayList<Justificacion>();
        
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOJustificacion.class.getName()).log(Level.SEVERE, null, ex);
            //Aqui estaba escrito DAOHorario, Jesus arreglar
        }
        final Map<String, Object> params = MapUtil.map( "Jus_tipo", "justificaciones" );               
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAOJustificacion");             
             Result resultfound = graphDb.execute( FindJustificacion(), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO justificacion Lista de justificaciones");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("n")).getProperty("Jus_id").toString();
                    int jus_id = Integer.parseInt(id);
                    String jus_nombre = ((Node) row.get("n")).getProperty("Jus_nombre").toString();
                    this._justificacion = FABRICAENTIDAD.obtenerJustificacion(jus_id, jus_nombre);
                    ListaJustificacion.add((Justificacion) _justificacion);                    
                }            
             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOJustificacion, Función Consultar justificaciones, Excepción NullPointer : " + NullPointerexcepcion);
                    //Jesus tenia DAOHorario corregir
                    throw NullPointerexcepcion;
                }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        System.out.println(ListaJustificacion);        
        return ListaJustificacion;
        
    }
}
