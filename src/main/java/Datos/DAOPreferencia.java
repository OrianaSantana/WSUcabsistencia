/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.Preferencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class DAOPreferencia extends DAOGeneral implements IDAOPreferencia{

    public ENTIDAD _preferencia = FABRICAENTIDAD.obtenerPreferencia(0, null, null, null, true);  

    private static String FindPreferencia(String id_profesor){    
        System.out.println("FIND ");
        //return "MATCH (n:Preferencia) RETURN n";
        return "match (m:Preferencia {Usu_id:"+ id_profesor+"})return m";
    }
    public enum NodeType implements Label{
        Preferencia;
    } 
    private static String UpdatePreferencias(String pref_nombre, String pref_status,String usu_id){
        System.out.println("UPDATE ");
        System.out.println(pref_status);
        pref_nombre= "'"+pref_nombre+"'";
        pref_status = "'"+pref_status+"'";
        System.out.println(pref_status);
        System.out.println(pref_nombre);
        //return "match (Preferencia {Pref_nombre:"+pref_nombre+", Usu_id:"+usu_id+"})set Preferencia.Pref_status="+pref_status;
        //String valor = "match (Preferencia {Pref_nombre:"+pref_nombre+", Usu_id:"+usu_id+"})set Preferencia.Pref_status="+pref_status+" return Preferencia";
        //System.out.println(valor);
        return "match (Preferencia {Pref_nombre:"+pref_nombre+", Usu_id:"+usu_id+"})SET Preferencia.Pref_status="+pref_status+" return Preferencia";
    }
    @Override
    public ArrayList<Preferencia> ConsultarPreferencia(String id_profesor) {
        ArrayList<Preferencia> ListaPreferencia = new ArrayList<Preferencia>();
        boolean status = false;
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Pref", "preferencias" );               
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAOPreferencia");             
             Result resultfound = graphDb.execute( FindPreferencia(id_profesor), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO preferencia Lista de preferencias");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("m")).getProperty("Pref_id").toString();
                    int pref_id = Integer.parseInt(id);
                    String pref_tipo = ((Node) row.get("m")).getProperty("Pref_tipo").toString();
                    System.out.println("TIPO preferencia" + pref_tipo);
                    String pref_nombre = ((Node) row.get("m")).getProperty("Pref_nombre").toString();
                    System.out.println("NOMBRE preferencia" + pref_nombre);
                    String pref_descripcion = ((Node) row.get("m")).getProperty("Pref_descripcion").toString();
                    System.out.println("Descripcion preferencia" + pref_descripcion);
                    String pref_status = ((Node) row.get("m")).getProperty("Pref_status").toString();    
                    if("off".equals(pref_status) ){
                        status = false;
                        System.out.println(status);
                    }else if ("on".equals(pref_status)){
                        status = true;
                        System.out.println(status);
                    }
                    System.out.println("STATUS preferencia " + pref_status);
                    this._preferencia = FABRICAENTIDAD.obtenerPreferencia(pref_id, pref_tipo,  pref_nombre, pref_descripcion, status);
                    ListaPreferencia.add((Preferencia) _preferencia);                     
                }            
             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOPreferencia, Función Consultar preferencias, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        System.out.println(ListaPreferencia);        
        return ListaPreferencia;
    }
    private String ArreglarPreferencias(String preferencias){
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
    @Override
    public boolean ModificarPreferencia(String preferencias){
        boolean respuesta = false;
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOPreferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Pref", "preferencias" );
        try (Transaction tx = graphDb.beginTx()){

            String str[] = ArreglarPreferencias(preferencias).split(",");
            //preferencias.split(",");               
            //System.out.println("Esta en el try " + str[1] + " Longitud " + str.length);
            System.out.println("TRY DAOPreferencia");   
        
            
            //CODIGO PARA TRATAR LA DATA QUE LLEGA DEL CLIENTE, DESPUES HAY QUE DEVOLVER LINEA Y CONVERTIRLO EN INPUT STREAM
            
           /*String linea = "@relation UCAB" + "\n" + "\n" + "@attribute x numeric" + "\n" + "@attribute y numeric" + "\n" + "@attribute z numeric" + "\n" + "@attribute clase {Pasillo,L1207,L1208,L1209,L1210,L1211,L1212,L1213}" + "\n" + "\n" + "@data"; 
            
             for (int i = 0; i < str.length; i++) { 
                 int a = i + 1;
                 int b = i + 2;
                 int c = i + 3;
               
                 System.out.println("a" + a);
                 System.out.println("b" + b);
                 System.out.println("c" + c);
                   
                linea = linea + "\n" + str[i] + "," + str[a] + "," + str[b] + "," + str[c] + "," + "?";
                
               i = i + 3;
                System.out.println("i" + i);
                System.out.println(linea);
             
             }*/
             
             
            int status = 2 , nombre = 1, usu_id = 3;
            for(int i = 0 ; i<=2; i++){
            System.out.println("Entro" + i);
            if("false".equals(str[status]) ){
                str[status] = "off";
            }else if ("true".equals(str[status])){
                str[status] = "on";
            }
            Result resultfound = graphDb.execute( UpdatePreferencias(str[nombre], str[status], str[usu_id]), params );          
            //graphDb.execute( UpdatePreferencias(str[1], str[2], str[3]));
            System.out.println(resultfound); 
            while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO preferencia Lista de preferencias");
                    Map<String,Object> row = resultfound.next();
                    String pref_tipo = ((Node) row.get("Preferencia")).getProperty("Pref_tipo").toString();
                    System.out.println("TIPO preferencia" + pref_tipo);
                    String pref_nombre = ((Node) row.get("Preferencia")).getProperty("Pref_nombre").toString();
                    System.out.println("NOMBRE preferencia" + pref_nombre);
                    String pref_descripcion = ((Node) row.get("Preferencia")).getProperty("Pref_descripcion").toString();
                    System.out.println("Descripcion preferencia" + pref_descripcion);
                    String pref_status = ((Node) row.get("Preferencia")).getProperty("Pref_status").toString();    
                    System.out.println("STATUS preferencia atualizado" + pref_status);
                }             
            System.out.println("str2 "+str[status]);
            status = status +4; nombre = nombre +4; usu_id = usu_id+4;
            System.out.println(" status "+ status + " nombre " + nombre + " usu_id "+ usu_id );
            }
            tx.success();
            respuesta = true; 
            
        }
        catch (NullPointerException NullPointerexcepcion){
            System.out.println("Error en DAOPreferencia, Función Modificar preferencias, Excepción NullPointer : " + NullPointerexcepcion);
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
