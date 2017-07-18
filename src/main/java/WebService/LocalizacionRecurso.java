/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Logica.FabricaComando;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Oriana
 */

@Path("ubicacion")
public class LocalizacionRecurso {
    
    @POST
    @Consumes("application/json")
    //@Produces("application/json") 
    //Ojo puede que este POST produzca algo, o simplemente se envie el resultado y se maneja en el cliente la forma de mostrar la data
    public Response createAsistencia(String prueba) throws UnsupportedEncodingException, Exception {
      System.out.println("POST LOCALIZACION" + prueba);
      String result = null;
    
     //El mapa se debe pedir una sola vez no siempre, validar eso
      String Mapa = FabricaComando.ObtenerComandoConstruirMapa().Ejecutar("");
      String Test = FabricaComando.ObtenerComandoConstruirTest().Ejecutar(prueba);
      
      //Clasificador
      
        int pasillo = 0; //0.0
        int l1207 = 0; // 1.0
        int l1208 = 0; // 2.0
        int l1209 = 0; // 3.0
        int l1210 = 0; // 4.0
        int l1211 = 0; // 5.0
        int l1212 = 0; // 6.0
        int l1213 = 0; // 7.0
        int ninguno = 0;
        
        //Se convierte a Input Stream tanto mapa como test
         InputStream MapaMagnetico = new ByteArrayInputStream(Mapa.getBytes("UTF-8"));
         InputStream TestPredecir = new ByteArrayInputStream(Test.getBytes("UTF-8"));
         
          // Clasificador, predice las clases 
       
          //Mapa
        DataSource mapahuella = new DataSource(MapaMagnetico);
        Instances train = mapahuella.getDataSet();
        train.setClassIndex(train.numAttributes() -1);
        
        //Test
        DataSource test = new DataSource(TestPredecir);
        Instances predict = test.getDataSet();
        predict.setClassIndex(predict.numAttributes() -1);
        
        IBk knn = new IBk();
      
        knn.setKNN(137);
        
        knn.buildClassifier(train);
        Instances labeled = new Instances(predict);
        
      for (int i = 0; i < predict.numInstances(); i++) {
        
            //double clsLabel = tree.classifyInstance(test.instance(i));
            double clsLabel = knn.classifyInstance(predict.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
            
            String condicion = Double.toString(clsLabel);
            
           switch (condicion) {
           
               case "0.0":
                   pasillo = pasillo + 1;
                   break;
               case "1.0":
                   l1207 = l1207 + 1;
                   break;
               case "2.0":
                   l1208 = l1208 + 1;
                   break;
               case "3.0":
                   l1209 = l1209 + 1;
                   break;
               case "4.0":
                   l1210 = l1210 + 1;
                   break;
               case "5.0":
                   l1211 = l1211 + 1;
                   break;
               case "6.0":
                   l1212 = l1212 + 1;
                   break;
               case "7.0": 
                   l1213 = l1213 + 1;
                   break;
               default:
                   ninguno = ninguno + 1;
           }
               
         
        }
       
      System.out.println(" ");
      System.out.println("RESULTADO DE LA PREDICCION");
      System.out.println(" ");
      System.out.println(labeled.toString());
      System.out.println ("Pasillo" + " " + pasillo);
      System.out.println ("L1207" + " " + l1207);
      System.out.println ("L1208" + " " + l1208);
      System.out.println ("L1209" + " " + l1209);
      System.out.println ("L1210" + " " + l1210);
      System.out.println ("L1211" + " " + l1211);
      System.out.println ("L1212" + " " + l1212);
      System.out.println ("L1213" + " " + l1213);
      
      //Se debe comparar que salon tiene mas clasificaciones para otorgarselo a result
         
      return Response.status(201).entity(result).build(); //validar el tipo de respuesta a obtener
    }  
    
}
