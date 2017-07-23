/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.FabricaDAO;
import Datos.IDAOHuella;
import Dominio.Huella;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author Oriana
 */
public class ComandoConstruirMapa extends ComandoGeneral{

    @Override
    public Object Ejecutar(Object parametro) {
    
        try
        {
           IDAOHuella _daoHuella;
            _daoHuella = FabricaDAO.ObtenerDAOHuella();
           System.out.println(" LLAMADO A FABRICA OBTENER DAOHuellaMapa");
           //Justificacion justificacion =(Justificacion) _daoJustificacion.ConsultarJustificacion();
           ArrayList<Huella> listaHuellas = new  ArrayList<>();
           listaHuellas = _daoHuella.ConsultarHuellasMagneticas();
           System.out.println("Lista huellas en comando construir "+listaHuellas);
           String resultadoHuellas = "";
           
           if (listaHuellas.isEmpty()) {
              resultadoHuellas = "";
           } else {
                  resultadoHuellas = "@relation UCAB" + "\n" + "\n" + "@attribute x numeric" + "\n" + "@attribute y numeric" + "\n" + "@attribute z numeric" + "\n" + "@attribute clase {Pasillo,L1207,L1208,L1209,L1210,L1211,L1212,L1213}" + "\n" + "\n" + "@data";      
                        for (int i = 0; i < listaHuellas.size(); i++) {         
                           resultadoHuellas = resultadoHuellas + "\n" + listaHuellas.get(i).hue_x + "," + listaHuellas.get(i).hue_y + "," + listaHuellas.get(i).hue_z + "," + listaHuellas.get(i).hue_salon;
                        }         
                System.out.println("Mapa Magnetico:" + resultadoHuellas);
           }
             
            return resultadoHuellas;
        }
        catch (Exception ex)
        {
             System.out.println("ENTRO EN LA EXCEPTION DE COMANDO CONSTRUIR MAPA J");
             JsonParser parser = new JsonParser();
             JsonObject o = (JsonObject)parser.parse("{\"respuesta\": \"No se pudo consultar la lista de huellas magneticas\"}");
             return o.toString();                      
        }
    }
    
}
