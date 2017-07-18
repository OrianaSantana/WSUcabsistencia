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
@Path("profesores/horarios")
@Produces("application/json")
public class HorarioProfesorRecurso {
    @GET    
    public String getHorario(@QueryParam("profesor-id")int id) {
        System.out.println("param "+id);
        String _id = String.valueOf(id);  
           
        //AQUI SE PROCESA EL STRING Y SE CAMBIA A INPUTSTREAM
    /*  String linea = FabricaComando.ObtenerComandoHorarioProfesor().Ejecutar(_id);
     
      InputStream MapaMagnetico = new ByteArrayInputStream(linea.getBytes("UTF-8"));
        
      
       System.out.println("Este es el inputStream:" + " " + MapaMagnetico);*/
     
        return FabricaComando.ObtenerComandoHorarioProfesor().Ejecutar(_id);
    }        
    
}
