/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;
import Dominio.HistoricoAsistencia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class DAOHistoricoAsistencia extends DAOGeneral implements IDAOHistoricoAsistencia {

    private ENTIDAD _historicoasistencia = FABRICAENTIDAD.obtenerHistoricoAsistencia(0, null, null, false, null, null,null);

  
    
    public enum RelationType implements RelationshipType{
        Asistio;
    }
    
    public enum NodeType implements Label{
        Historico_Asistencia, Horario, Justificacion;
    }
    
    private static String AsistenciasID(String id_horario){
        System.out.println("ASISTENCIAS ID");
        return "MATCH (Historico_Asistencia{Asis_Hor_id:"+"'"+id_horario+"'"+"}) RETURN Historico_Asistencia";
        
    }
    
    private String ArreglarListaAsistencia(String asistencia){
        asistencia = asistencia.replace("[", "");
        asistencia = asistencia.replace("]", "");
        asistencia = asistencia.replace("fecha", "");
        asistencia = asistencia.replace("estado", "");
        asistencia = asistencia.replace("tipo", "");
        asistencia = asistencia.replace("hor_id", "");       
        asistencia = asistencia.replace("jus_id", "");  
        asistencia = asistencia.replace("observacion", "");
        asistencia = asistencia.replace("\"", "");
        asistencia = asistencia.replace(":", "");
        return asistencia;
    }
    public String EliminarEspacioEnBlanco(String eliminar){
        eliminar = eliminar.replace(" ", "");
        return eliminar;
    }
    @Override
    public boolean CrearAsistenciaManual(String asistencia){
        System.out.println("Asistenciaaaa" + asistencia);
        boolean respuesta = false;
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOPreferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Hor_tipo", "horarios" );
        try (Transaction tx = graphDb.beginTx()){
            String str[] = ArreglarListaAsistencia(asistencia).split(",");
            for (int i = 0; i<str.length;i++)
            {   
                if(str[i].length()>1){
                  str[i] =  EliminarEspacioEnBlanco(str[i]);
                }
            }
            Node Asistencia = graphDb.createNode(NodeType.Historico_Asistencia);
            Asistencia.setProperty("Asis_fecha", str[0]);
            Asistencia.setProperty("Asis_estado",str[1]);
            Asistencia.setProperty("Asis_tipo_asistencia",str[2]);
            Asistencia.setProperty("Asis_Hor_id", str[3]);
            Asistencia.setProperty("Asis_Jus_id",str[4]);
            Asistencia.setProperty("Asis_Observacio", str[5]);
            Asistencia.setProperty("Asis_tipo", "Asistencia");
            ResourceIterator<Node> providers = graphDb.findNodes(NodeType.Horario);
            while (providers.hasNext()) {
		final Node recordNode = providers.next();               
                if (recordNode.getProperty("Hor_id").toString().equals(str[3]))
                {   
                    System.out.println("recordNode  "+ recordNode+ "  ID  "+ recordNode.getProperty("Hor_id")+ "  catedra  " + recordNode.getProperty("Hor_catedra"));
                    recordNode.createRelationshipTo(Asistencia, RelationType.Asistio);
                }
                else {
                    System.out.println("No encontrado "  + recordNode.getProperty("Hor_id"));
                }
	    }
            tx.success();
            respuesta = true; 
        }
        catch (NullPointerException NullPointerexcepcion){
            System.out.println("Error en DAOHistorico, Función CREAR asistencia, Excepción NullPointer : " + NullPointerexcepcion);
            respuesta = false;
            throw NullPointerexcepcion;   
        }
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }

        
        return respuesta;
    }
    
      @Override
    public ArrayList<HistoricoAsistencia> asistencias(String id_horario) {
          ArrayList<HistoricoAsistencia> ListaAsistencias = new ArrayList<>();
        System.out.println("ARRAYLISTA");
        GraphDatabaseService graphDb = null;
        try {
            graphDb = DAOGeneral.IniciarConexion();            
        } catch (Exception ex) {
            Logger.getLogger(DAOHistoricoAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Map<String, Object> params = MapUtil.map( "Asis_tipo", "Asistencia" ); 
        try (Transaction tx = graphDb.beginTx()){            
             System.out.println("TRY DAOHistoricoAsistencia");             
             Result resultfound = graphDb.execute( AsistenciasID(id_horario), params );             
             System.out.println(resultfound); 
             while (resultfound.hasNext())
                {      
                    System.out.println("WHILE DAO HistoricoAsistencia Lista de asistencias");
                    Map<String,Object> row = resultfound.next();
                    String id =((Node) row.get("Historico_Asistencia")).getProperty("Asis_Hor_id").toString();
                    int hist_asis_id = Integer.parseInt(id);
                    String hist_asis_fecha = ((Node) row.get("Historico_Asistencia")).getProperty("Asis_fecha").toString();                  
                    String hist_asis_tipo = ((Node) row.get("Historico_Asistencia")).getProperty("Asis_tipo_asistencia").toString();
                    String id_justificacion = ((Node) row.get("Historico_Asistencia")).getProperty("Asis_Jus_id").toString();
                    String hist_asis_justificacion = "";
                    ResourceIterator<Node> providers = graphDb.findNodes(NodeType.Justificacion);
                    while (providers.hasNext()) {
                        final Node recordNode = providers.next();               
                        if (recordNode.getProperty("Jus_id").toString().equals(id_justificacion))
                        {   
                            hist_asis_justificacion = recordNode.getProperty("Jus_nombre").toString();
                        }
                        else {
                            System.out.println("No encontrado "  + recordNode.getProperty("Jus_id"));
                        }
                    }
                    
                    SimpleDateFormat simpleDate = new SimpleDateFormat ("dd/M/yyyy");
                    Date date = simpleDate.parse(hist_asis_fecha);
                    System.out.println("justificacion " + hist_asis_justificacion);
                    this._historicoasistencia = FABRICAENTIDAD.obtenerHistoricoAsistencia(hist_asis_id, simpleDate.format(date), hist_asis_tipo, false,hist_asis_justificacion,null,null);
                    ListaAsistencias.add((HistoricoAsistencia) _historicoasistencia);
                }            
             
        }
        catch(NullPointerException NullPointerexcepcion)
                {                      
                    System.out.println("Error en DAOHistoricoAsistencia, Función Consultar asistencias, Excepción NullPointer : " + NullPointerexcepcion);
                    throw NullPointerexcepcion;
                } catch (Exception ex) {        
            Logger.getLogger(DAOHistoricoAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally{
            graphDb.shutdown();
            System.out.println("SHUTDOWN");
        }
        
     
        System.out.println(ListaAsistencias); 
        
       
        
        return ListaAsistencias;  
    }

    
}
