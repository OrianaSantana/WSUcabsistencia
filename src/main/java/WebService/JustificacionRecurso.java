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
@Path("justificaciones")
@Produces("application/json")
public class JustificacionRecurso {
    @GET    
    public String getjustificacion(){
        return FabricaComando.ObtenerComandoJustificacion().Ejecutar("");
    }        
    
}
