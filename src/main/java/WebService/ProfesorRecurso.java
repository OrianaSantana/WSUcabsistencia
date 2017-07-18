/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Logica.FabricaComando;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author el_je_000
 */
@Path("profesores")
@Produces("application/json")
public class ProfesorRecurso {    
    @GET    
    public String get(@QueryParam("profesor-id")int id){
        System.out.println("param "+id);
        String _id = String.valueOf(id);
        //System.out.println(FabricaComando.ObtenerComandoProfesor().Ejecutar(_id));
        return FabricaComando.ObtenerComandoProfesor().Ejecutar(_id);
    }        
}
