/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Dominio.HistoricoAsistencia;
import Logica.FabricaComando;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
/**
 *
 * @author el_je_000
 */
@Path("asistencias")
public class HistoricoAsistenciaRecurso {

    @POST
    @Consumes("application/json")
    //@Produces("application/json")
    public Response createAsistencia(String prueba) {
      System.out.println("POST" + prueba);
      String result = prueba;
      FabricaComando.ObtenerComandoCrearAsistencia().Ejecutar(prueba);
      return Response.status(201).entity(result).build();
    }    
    
    @GET  
    @Path("horarios")
    @Produces("application/json")
    public String getAsistencia (@QueryParam("horario-id")String id){
        System.out.println("Param " + id);    
        ArrayList<HistoricoAsistencia> listaAsistencias = new  ArrayList<>();
        
           listaAsistencias.add(0,new HistoricoAsistencia(1,"17/6/2017","manual",false,null,null,null));
           listaAsistencias.add(1,new HistoricoAsistencia(2,"17/6/2017","automatica",false,null,null,null));
           listaAsistencias.add(2,new HistoricoAsistencia(1,"17/6/2017","inasistencia",false,null,null,null));
           String asistencias = listaAsistencias.toString();
            asistencias = asistencias.replace("Historico Asistencia", "");   
       return FabricaComando.ObtenerComandoConsultarAsistencias().Ejecutar(id); 
              
    }
    
}
