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
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
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
       Historico_Notificacion, Usuario,secuenciaID;
    }
       public enum RelationType implements RelationshipType{
        Se_notifica_a;
    }
     private static String FindNotificacion(String id_profesor){    
        System.out.println("FIND "+id_profesor);
        //return "Match (n: Notificacion)-[Se_notifica_a]->(Usuario {Usu_id:"+id_profesor+"}) Return n";
        return "Match (n: Historico_Notificacion)<-[Se_notifica_a]-(Usuario {Usu_id:"+id_profesor+"}) Return n";
    }
    public String EliminarEspacioEnBlanco(String eliminar){
        eliminar = eliminar.replace(" ", "");
        return eliminar;
    }
    private String ArreglarListaAsistencia(String asistencia){
        asistencia = asistencia.replace("[", "");
        asistencia = asistencia.replace("]", "");
        asistencia = asistencia.replace("usu_id", "");
        asistencia = asistencia.replace("mensaje", "");
        asistencia = asistencia.replace("\"", "");
        asistencia = asistencia.replace(":", "");
        return asistencia;
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
     @Override
     public boolean CrearNotificacion(String notificaciones){
        System.out.println("Notificaciones" + notificaciones);
        boolean respuesta = false;
        String id= "";
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOPreferencia.class.getName()).log(Level.SEVERE, null, ex);
        }        
        final Map<String, Object> params = MapUtil.map( "Sec_descripcion", "secuenicas" );
        final Map<String, Object> params1 = MapUtil.map( "Usu_tipo", "Profesor" );        
        try (Transaction tx = graphDb.beginTx()){
            String str[] = ArreglarListaAsistencia(notificaciones).split(",");
            for (int i = 0; i<str.length;i++)
            {   
                if(str[i].length()>1){
                  str[i] =  EliminarEspacioEnBlanco(str[i]);
                }
            }
            ResourceIterator<Node> providers = graphDb.findNodes(NodeType.secuenciaID);
            while (providers.hasNext()) {
                   final Node recordNode1 = providers.next();               
                   String i = recordNode1.getProperty("Sec_Historico_Notificaciones").toString();
                   int number = Integer.parseInt(i);
                   number = number + 1;
                   id = String.valueOf(number);
                   recordNode1.setProperty("Sec_Historico_Notificaciones", id);
                   tx.success();
             }            
            
            Node Notificacion = graphDb.createNode(NodeType.Historico_Notificacion);
            Notificacion.setProperty("Not_tipo", "notificaciones");
            Notificacion.setProperty("Not_id",id);
            Notificacion.setProperty("Not_mensaje",str[1]);            
            ResourceIterator<Node> providers1 = graphDb.findNodes(NodeType.Usuario);
            while (providers1.hasNext()) {
                System.out.println("str  "+str[0]);
		final Node recordNode = providers1.next();  
                System.out.println("str  "+str[0]);
                if (recordNode.getProperty("Usu_id").toString().equals(str[0]))
                {   
                    System.out.println("recordNode  "+ recordNode+ "  ID  "+ recordNode.getProperty("Usu_id"));
                    recordNode.createRelationshipTo(Notificacion, RelationType.Se_notifica_a);
                }
                else {
                    System.out.println("No encontrado "  + recordNode.getProperty("Usu_id"));
                }
	    }
            tx.success();
            respuesta = true; 
        }
        catch (NullPointerException NullPointerexcepcion){
            System.out.println("Error en DAONotificacion, Función CREAR notificacion, Excepción NullPointer : " + NullPointerexcepcion);
            respuesta = false;
            throw NullPointerexcepcion;   
        }
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }        
        return respuesta;
    }
}