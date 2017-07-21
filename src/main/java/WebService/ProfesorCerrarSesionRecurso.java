/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Logica.FabricaComando;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author el_je_000
 */
@Path("profesor/cerrar/conexion")
public class ProfesorCerrarSesionRecurso {
    @PUT    
    @Consumes("application/json")
    //@Produces("application/json")
    public Response cerrarConexion(String prueba) {
      System.out.println("PUT" + prueba);
      String result = prueba;
      FabricaComando.ObtenerComandoCerrarConexion().Ejecutar(prueba);
      return Response.status(201).entity(result).build();
    }             
}
