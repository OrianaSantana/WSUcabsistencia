/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.Profesor;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.cypher.internal.ExecutionEngine;
import org.neo4j.cypher.internal.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import static org.neo4j.graphdb.QueryExecutionType.query;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.MapUtil;

/**
 *
 * @author el_je_000
 */
public class DAOProfesor extends DAOGeneral implements IDAOProfesor {
    
    public ENTIDAD _profesor =  FABRICAENTIDAD.obtenerProfesor(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    
    public enum NodeType implements Label{
        Profesor, Usuario;
    }
    private static String FindUserByID(String id_profesor){    
        System.out.println("FIND "+id_profesor);
        return "MATCH (Usuario {Usu_id:"+id_profesor+"}) RETURN Usuario";
    }
    private static String FindUserByEmail(String correo_profesor){    
        System.out.println("FIND EMAIL "+correo_profesor);        
        return "MATCH (Usuario {Usu_correo:"+correo_profesor+"}) RETURN Usuario";
    }
    private static String BuscarSecuencia(){
        return "MATCH (n:secuenciaID) RETURN n";
    }
private String Arreglarstatus(String preferencias){
             preferencias = preferencias.replace("[", "");
             preferencias = preferencias.replace("]", "");
             preferencias = preferencias.replace("pre_id", "");
             preferencias = preferencias.replace("pre_nombre", "");
             preferencias = preferencias.replace("pre_status", "");
             preferencias = preferencias.replace("usu_id", "");
             preferencias = preferencias.replace("\"", "");
             preferencias = preferencias.replace(":", "");
        return preferencias;
    }
    public String EliminarEspacioEnBlanco(String eliminar){
        eliminar = eliminar.replace(" ", "");
        return eliminar;
    }    
    @Override
    public Dominio.ENTIDAD ConsultarProfesorPorID (String id_profesor)
    {
        System.out.println("ID "+id_profesor);
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();
            //graphDb = DAOGeneral.getDb();

        } catch (Exception ex) {
            Logger.getLogger(DAOProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final Map<String, Object> params = MapUtil.map( "Usu_tipo", "Profesor" );               
        try (Transaction tx = graphDb.beginTx()){
            
             System.out.println("TRY DAOPROFESOR");
             
             Result resultfound = graphDb.execute( FindUserByID(id_profesor), params );             
             System.out.println(resultfound);             
                                                                 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAOPROFESOR");
                    // FALTA HACER LAS NOTIFICACIONES, ACERCA DE, COMO FUNCIONA, ROL, PREFERENCIAS, HORARIOS
                    Map<String,Object> row = resultfound.next();
                    //System.out.println("row");                                         
                    String id =((Node) row.get("Usuario")).getProperty("Usu_id").toString();
                    int usu_id = Integer.parseInt(id);
                    System.out.println(usu_id);
                    String usu_foto = ((Node) row.get("Usuario")).getProperty("Usu_foto").toString();
                    System.out.println("status de conexion "+usu_foto);
                    String usu_correo = ((Node) row.get("Usuario")).getProperty("Usu_correo").toString();
                    System.out.println(usu_correo);
                    String usu_contraseña = ((Node) row.get("Usuario")).getProperty("Usu_contraseña").toString();
                    System.out.println(usu_contraseña);
                    String usu_primer_nombre = ((Node) row.get("Usuario")).getProperty("Usu_primer_nombre").toString();
                    //String usu_primer_nombre = ((Node) row.get("Usuario")).getProperty("Usu_nombre").toString();                    
                    System.out.println(usu_primer_nombre); 
                    String usu_segundo_nombre = ((Node) row.get("Usuario")).getProperty("Usu_segundo_nombre").toString();                    
                    System.out.println(usu_segundo_nombre);
                    String usu_primer_apellido = ((Node) row.get("Usuario")).getProperty("Usu_primer_apellido").toString();
                    System.out.println(usu_primer_apellido);
                    String usu_segundo_apellido = ((Node) row.get("Usuario")).getProperty("Usu_segundo_apellido").toString();                    
                    System.out.println(usu_segundo_apellido);       
                    String usu_tipo = ((Node) row.get("Usuario")).getProperty("Usu_tipo").toString();
                    System.out.println(usu_tipo);                    
                    return this._profesor = FABRICAENTIDAD.obtenerProfesor(usu_id,((Node) row.get("Usuario")).getProperty("Usu_foto").toString(),((Node) row.get("Usuario")).getProperty("Usu_correo").toString(),((Node) row.get("Usuario")).getProperty("Usu_contraseña").toString(),((Node) row.get("Usuario")).getProperty("Usu_primer_nombre").toString(),((Node) row.get("Usuario")).getProperty("Usu_segundo_nombre").toString(),((Node) row.get("Usuario")).getProperty("Usu_primer_apellido").toString(),((Node) row.get("Usuario")).getProperty("Usu_segundo_apellido").toString(),((Node) row.get("Usuario")).getProperty("Usu_tipo").toString(),null,null,null,null,null,null);

                }  
            tx.success(); 
            System.out.println("Termino TRY");
        }
         catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOProfesor, Función ConsultarProfesorPorID, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        catch(Exception e)
        {
            System.out.println("Error en DAOProfesor");
            throw e;
        }finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        return null;
    }
    
    @Override    
    public Dominio.ENTIDAD ConsultarProfesorPorCorreo(String correo_profesor)
    {
        System.out.println("CORREO "+correo_profesor);
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            

        } catch (Exception ex) {
            Logger.getLogger(DAOProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Usu_tipo", "Profesor" );               
                
        try (Transaction tx = graphDb.beginTx())
        {
             System.out.println("TRY DAOPROFESOR");            
             String buscar_correo_profesor = "'"+correo_profesor+"'";
             Result resultfound = graphDb.execute( FindUserByEmail(buscar_correo_profesor), params);
             System.out.println(resultfound);
             while (resultfound.hasNext())
                {         
                    System.out.println("WHILE DAOPROFESOR");
                    Map<String,Object> row = resultfound.next();                                                     
                    String id =((Node) row.get("Usuario")).getProperty("Usu_id").toString();
                    int usu_id = Integer.parseInt(id);  
                    return this._profesor = FABRICAENTIDAD.obtenerProfesor(usu_id,((Node) row.get("Usuario")).getProperty("Usu_foto").toString(),((Node) row.get("Usuario")).getProperty("Usu_correo").toString(),((Node) row.get("Usuario")).getProperty("Usu_contraseña").toString(),((Node) row.get("Usuario")).getProperty("Usu_primer_nombre").toString(),((Node) row.get("Usuario")).getProperty("Usu_segundo_nombre").toString(),((Node) row.get("Usuario")).getProperty("Usu_primer_apellido").toString(),((Node) row.get("Usuario")).getProperty("Usu_segundo_apellido").toString(),((Node) row.get("Usuario")).getProperty("Usu_tipo").toString(),null,null,null,null,null,null);
                    
                }                        
            tx.success();  
            System.out.println("Termino TRY");
        }
         catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOUsuario, Función ConsultarProfesorPorCorreo, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        catch(Exception e)
        {
            System.out.println("Error en DAOProfesor");
            throw e;
        }finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        return null;        
    }  
  /*  public boolean ValidarProfesorPorCorreo(String correo_profesor)
    {
        GraphDatabaseService graphDb;
        graphDb = DAOGeneral.getDb();
        final Map<String, Object> params = MapUtil.map( "Usu_correo", correo_profesor );
        
        try (Transaction tx = graphDb.beginTx()){
            Result resultfound = graphDb.execute( FindUserByEmail(correo_profesor), params);
            if (resultfound.hasNext())
            {
                return true;
            }
            else{
                return false;
            }                                                                  
        }
         catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOUsuario, Función ConsultarProfesorPorCorreo, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        catch(Exception e)
        {
            throw e;
        }
        
    }      
    public boolean InsertarProfesor(Profesor profesor)
    {
        GraphDatabaseService graphDb;
        graphDb = DAOGeneral.getDb();
        boolean validarUsuario = ValidarProfesorPorCorreo(profesor.getPro_correo_nombre_usuario());
        if (!validarUsuario)
        {
            Node ProfesorNode = graphDb.createNode(NodeType.Profesor);
            ProfesorNode.setProperty("Usu_id", 5);
        }
        
        return true;
    }*/
    public static int secuencia(GraphDatabaseService graphDb){
        final Map<String, Object> buscarsecuencia = MapUtil.map( "Sec_descripcion", "secuenicas");
             Result resultchange = graphDb.execute( BuscarSecuencia(), buscarsecuencia);
             while (resultchange.hasNext())
                {
                        Map<String,Object> row = resultchange.next();
                        String secuenciaID = ((Node) row.get("n")).getProperty("Sec_usuario").toString();
                        int sec = Integer.parseInt(secuenciaID)+1;
                        ((Node) row.get("n")).setProperty("Sec_usuario", sec);                        
                    return sec;
                }
        return 1;

    }
    @Override
    public boolean ModificarStatusConexion(String profesor){
        System.out.println("DAOPRODESOR "+profesor);
        boolean respuesta = false;
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOPreferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Usu_tipo", "Profesor" );               
        try (Transaction tx = graphDb.beginTx()){
    
            String str[] = Arreglarstatus(profesor).split(",");
            for (int i = 0; i<str.length;i++)
            {   
                if(str[i].length()>1){
                  str[i] =  EliminarEspacioEnBlanco(str[i]);
                }
            }            
            ResourceIterator<Node> providers = graphDb.findNodes(NodeType.Usuario);
            while (providers.hasNext()) {
                final Node recordNode = providers.next();  
                if (recordNode.getProperty("Usu_id").toString().equals(str[1]))
                {   
                    System.out.println("recordNode  "+ recordNode+ "  ID  "+ recordNode.getProperty("Usu_id"));
                    recordNode.setProperty("Usu_foto", "on");                    
                }
                else {
                    System.out.println("No encontrado "  + recordNode.getProperty("Usu_id"));
                }                
	    }             
            tx.success();
            respuesta = true; 
        }
        catch (NullPointerException NullPointerexcepcion){
            System.out.println("Error en DAOProfesor, Función Modificar status conexion profesor, Excepción NullPointer : " + NullPointerexcepcion);
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
