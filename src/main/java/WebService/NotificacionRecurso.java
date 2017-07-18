/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Logica.FabricaComando;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author el_je_000
 */

@Path("profesores/notificaciones")
@Produces("application/json")
public class NotificacionRecurso {
    @GET    
    public String getNotificacion(@QueryParam("profesor-id")int id){
        System.out.println("param "+id);
        String _id = String.valueOf(id);                
        return FabricaComando.ObtenerComandoNotificacion().Ejecutar(_id);
    }        
    
    
}
