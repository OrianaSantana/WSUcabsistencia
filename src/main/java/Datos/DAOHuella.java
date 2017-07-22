/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.Huella;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.MapUtil;


/**
 *
 * @author el_je_000
 */
public class DAOHuella extends DAOGeneral implements IDAOHuella {

   public ENTIDAD _huella = FABRICAENTIDAD.obtenerHuella(0, 0, 0, 0, null,null);
   
    public enum NodeType implements Label{
        Huella, Log, secuenciaID;
    }
    private static String FindHuella(){    
        System.out.println("FIND ");
        return "MATCH (n:Huella) RETURN n";
    }
   
    private String ArreglarTest(String huellasTest){
             huellasTest = huellasTest.replace("[", "");
             huellasTest = huellasTest.replace("]", "");
             huellasTest = huellasTest.replace("vx", "");
             huellasTest = huellasTest.replace("vy", "");
             huellasTest = huellasTest.replace("vz", "");
             huellasTest = huellasTest.replace("\"", "");
             huellasTest = huellasTest.replace(":", "");
             huellasTest = huellasTest.replace("pro_id", "");
             huellasTest = huellasTest.replace(" ", "");
        return huellasTest;
    }
    
   @Override
    public String ModificarTest(String TestHuellas){
        String respuesta = "@relation UCAB" + "\n" + "\n" + "@attribute x numeric" + "\n" + "@attribute y numeric" + "\n" + "@attribute z numeric" + "\n" + "@attribute clase {Pasillo,L1207,L1208,L1209,L1210,L1211,L1212,L1213}" + "\n" + "\n" + "@data" + "\n";
      
        System.out.println("VALOR INICIAL DEL OBJETO A GUARDAR EN ARFF");
        System.out.println(respuesta);
        System.out.println(" ");
        
        try {

            String str[] = ArreglarTest(TestHuellas).split(",");
           
            System.out.println("TRY DAOHuellaTest");   
        
            
            //Se modifica el test, se envia al comando, del comando al servicio y alli se convierte en InputStream
            
          
             for (int i = 0; i < str.length; i++) { 
                 int a = i + 1;
                 int b = i + 2;
               
                 System.out.println("a" + a);
                 System.out.println("b" + b);
                 
                respuesta = respuesta + str[i] + "," + str[a] + "," + str[b] + "," + "?" + "\n";
                
               i = i + 3;
               
                System.out.println("i" + i);
                System.out.println(respuesta);
             
             }
             
         System.out.println("OBJETO A ALMACENAR TEST A PREDECIR");
         System.out.println(respuesta);
             
           
            }
        
        catch (NullPointerException NullPointerexcepcion){
            System.out.println("Error en DAOHuellaTest, Función Modificar Test, Excepción NullPointer : " + NullPointerexcepcion);
            respuesta = null;
            throw NullPointerexcepcion;   
        }
        finally{
            System.out.println("SHUTDOWN");
        }

        return respuesta;
    }

    @Override
    public ArrayList<Huella> ConsultarHuellasMagneticas() {
       
        ArrayList<Huella> ListaHuella = new ArrayList<>();
        String Secuencia_id = "";
        String Secuencia_log = "";
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHuella.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "hue_tipo", "huellas" ); //Preguntar jesus si dejo el hue_tipo
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAOHuellaMapa");
             ResourceIterator<Node> providers = graphDb.findNodes(DAOHuella.NodeType.secuenciaID);
             while (providers.hasNext()) {
                       final Node recordNode1 = providers.next();               
                       Secuencia_id = recordNode1.getProperty("Sec_huellas").toString();
             }            
             System.out.println("secuencia huellas " + Secuencia_id);
             
             ResourceIterator<Node> providers1 = graphDb.findNodes(DAOHuella.NodeType.Log);
             while (providers1.hasNext()) {
                       final Node recordNode2 = providers1.next();               
                       Secuencia_log = recordNode2.getProperty("Log_huella").toString();
             }            
             System.out.println("secuencia log " + Secuencia_log);
             if(Secuencia_log.equals(Secuencia_id)){
                 System.out.println("Son iguales");
             }
             else{
                Result resultfound = graphDb.execute( FindHuella(), params );             
                System.out.println(resultfound); 
                while (resultfound.hasNext())
                   {      
                       System.out.println("WHILE DAO huella Lista de huellas");
                       Map<String,Object> row = resultfound.next();
                       String id =((Node) row.get("n")).getProperty("hue_id").toString();
                       int hue_id = Integer.parseInt(id);
                       String x = ((Node) row.get("n")).getProperty("hue_x").toString();
                       float hue_x = Integer.parseInt(x);
                       String y = ((Node) row.get("n")).getProperty("hue_y").toString();
                       float hue_y = Integer.parseInt(y); 
                       String z = ((Node) row.get("n")).getProperty("hue_z").toString();
                       float hue_z = Integer.parseInt(z); 
                       String hue_salon = ((Node) row.get("n")).getProperty("hue_salon").toString();

                       this._huella = FABRICAENTIDAD.obtenerHuella(hue_id,hue_x,hue_y,hue_z,null,hue_salon);
                       ListaHuella.add((Huella) _huella);                    
                   }
                    ResourceIterator<Node> providers3 = graphDb.findNodes(DAOHuella.NodeType.Log);
                    while (providers3.hasNext()) {
                              final Node recordNode3 = providers3.next();               
                              recordNode3.setProperty("Log_huella",Secuencia_id );
                              tx.success();                              
                    }                            
             }             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOHuella, Función Consultar huellas magneticas, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        System.out.println(ListaHuella);        

        return ListaHuella;              
    }
    
}
