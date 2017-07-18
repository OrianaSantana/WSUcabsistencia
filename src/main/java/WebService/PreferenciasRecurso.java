/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Logica.FabricaComando;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
/**
 *
 * @author el_je_000
 */
@Path("preferencias")
//@Produces("application/json")
public class PreferenciasRecurso {
    
    @GET  
    @Path("profesores")
    @Produces("application/json")
    public String getpreferencia(@QueryParam("profesor-id")String id){
        System.out.println("Param " + id);
        return FabricaComando.ObtenerComandoPreferencia().Ejecutar(id);
    }
    
    @PUT
    @Path("modificar")
    @Consumes("application/json")
    //@Produces("application/json")
    public Response updatePreferencias(String prueba) {
      System.out.println("PUT" + prueba);
      String result = prueba;
      FabricaComando.ObtenerComandoModificarPreferencia().Ejecutar(prueba);
      return Response.status(201).entity(result).build();
    } 
}
