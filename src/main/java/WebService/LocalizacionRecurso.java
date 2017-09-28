/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Logica.FabricaComando;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public String localizarProfesor (String prueba) throws UnsupportedEncodingException, Exception {
      System.out.println("POST LOCALIZACION" + prueba);
      String result = "";
    
      //El mapa o viene lleno o viene vacio, 2 condiciones
     String Mapa = FabricaComando.ObtenerComandoConstruirMapa().Ejecutar("");
     System.out.println("MAPA " + Mapa);
     
     String Test = FabricaComando.ObtenerComandoConstruirTest().Ejecutar(prueba);
     
       //InputStream TestPredecir = new ByteArrayInputStream(Test.getBytes("UTF-8"));
      
         FileWriter fileTest = new FileWriter("C:\\Users\\el_je_000\\Documents\\GitHub\\WSUcabsistencia\\TestMagnetico.arff");
         fileTest.write(Test);
         fileTest.flush();
       
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
        
         //Test
      //  DataSource test = new DataSource(TestPredecir); //TestInputStream
       DataSource test = new DataSource("C:\\Users\\el_je_000\\Documents\\GitHub\\WSUcabsistencia\\TestMagnetico.arff");
       Instances predict = test.getDataSet();
       predict.setClassIndex(predict.numAttributes() -1);
       
        IBk knn = new IBk();
      
        knn.setKNN(249);
       
        if (Mapa.isEmpty()) {
       
      // Clasificador, predice las clases 
       
          //Mapa
      //  DataSource mapahuella = new DataSource(MapaMagnetico); //Mapa InputStream
        DataSource mapahuella = new DataSource("C:\\Users\\el_je_000\\Documents\\GitHub\\WSUcabsistencia\\MapaMagnetico.arff");
        Instances train = mapahuella.getDataSet();
        train.setClassIndex(train.numAttributes() -1);
        
        
        knn.buildClassifier(train);
        Instances labeled = new Instances(predict);
        
      for (int i = 0; i < predict.numInstances(); i++) {
        
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
      System.out.println ("Ninguno" + " " + ninguno);
      
     if ((pasillo > l1207) && (pasillo > l1208) && (pasillo > l1209) && (pasillo > l1210) && (pasillo > l1211) &&
         (pasillo > l1212) && (pasillo > l1213) && (pasillo > ninguno)) { 
    System.out.println ("Estoy en: " + pasillo);
    result = "Pasillo";
    
} else if  ((l1207 > l1208) && (l1207 > l1209) && (l1207 > l1210) && (l1207 > l1211) && (l1207 > l1212) && 
        (l1207 > l1213) && (l1207 > ninguno) && (l1207 > pasillo)){     
    System.out.println ("Estoy en: " + l1207);
    result = "L1207";
} else if ((l1208 > pasillo) && (l1208 > l1207) && (l1208 > l1209) && (l1208 > l1210) && (l1208 > l1211) &&(l1208 > l1212) && 
        (l1208 > l1213) && (l1208 > ninguno)) {                 
    System.out.println ("Estoy en: " + l1208);
    result = "L1208";
} else if ((l1209 > pasillo) && (l1209 > l1207) && (l1209 > l1208) && (l1209 > l1210) && (l1209 > l1211) && (l1209 > l1212) && 
        (l1209 > l1213) && (l1209 > ninguno)) {                               
    System.out.println ("Estoy en: " + l1209);
    result = "L1209";
} else if ((l1210 > pasillo) && (l1210 > l1207) && (l1210 > l1208) && (l1210 > l1209) && (l1210 > l1211) && 
        (l1210 > l1212) && (l1210 > l1213) && (l1210 > ninguno)) {
    System.out.println ("Estoy en: " + l1210);
    result = "L1210";
} else if ((l1211 > pasillo) && (l1211 > l1207) && (l1211 > l1208) && (l1211 > l1209) && (l1211 > l1210) && (l1211 > l1212) && 
        (l1211 > l1213) && (l1211 > ninguno)) {
    System.out.println ("Estoy en: " + l1211);
    result = "L1211";
} else if ((l1212 > pasillo) && (l1212 > l1207) && (l1212 > l1208) && (l1212 > l1209) && (l1212 > l1210) && (l1212 > l1211) &&
        (l1212 > l1213) && (l1212 > ninguno)) {
    System.out.println ("Estoy en: " + l1212);
    result = "L1212";
} else if ((l1213 > pasillo) && (l1213 > l1207) && (l1213 > l1208) && (l1213 > l1209) && (l1213 > l1210) && (l1213 > l1211) &&
        (l1213 > l1212) && (l1213 > ninguno)) {
    System.out.println ("Estoy en: " + l1213);
    result = "L1213";
} else if ((ninguno > pasillo) && (ninguno > l1207) && (ninguno > l1208) && (ninguno > l1209) && (ninguno > l1210) && 
        (ninguno > l1211) && (ninguno > l1212) && (ninguno > l1213)){                                           
    System.out.println ("Estoy en: " + ninguno);
    result = "Ninguno";
}
        
        } else {
            
         //Se actualiza el archivo existente
         FileWriter fileMap = new FileWriter("C:\\Users\\el_je_000\\Documents\\GitHub\\WSUcabsistencia\\MapaMagnetico.arff");
         fileMap.write(Mapa);
         fileMap.flush();
        
        DataSource mapahuella = new DataSource("C:\\Users\\el_je_000\\Documents\\GitHub\\WSUcabsistencia\\MapaMagnetico.arff");
        
        //Solo se puede hacer Input Stream cuando el mapa esta lleno, por archivo no se puede
       // InputStream MapaMagnetico = new ByteArrayInputStream(Mapa.getBytes("UTF-8"));
       
        Instances train = mapahuella.getDataSet();
        train.setClassIndex(train.numAttributes() -1);
        
        
        knn.buildClassifier(train);
        Instances labeled = new Instances(predict);
        
      for (int i = 0; i < predict.numInstances(); i++) {
        
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
      System.out.println ("Ninguno" + " " + ninguno);
      
     if ((pasillo > l1207) && (pasillo > l1208) && (pasillo > l1209) && (pasillo > l1210) && (pasillo > l1211) &&
         (pasillo > l1212) && (pasillo > l1213) && (pasillo > ninguno)) { 
    System.out.println ("Estoy en: " + pasillo);
    result = "Pasillo";
    
} else if  ((l1207 > l1208) && (l1207 > l1209) && (l1207 > l1210) && (l1207 > l1211) && (l1207 > l1212) && 
        (l1207 > l1213) && (l1207 > ninguno) && (l1207 > pasillo)){     
    System.out.println ("Estoy en: " + l1207);
    result = "L1207";
} else if ((l1208 > pasillo) && (l1208 > l1207) && (l1208 > l1209) && (l1208 > l1210) && (l1208 > l1211) &&(l1208 > l1212) && 
        (l1208 > l1213) && (l1208 > ninguno)) {                 
    System.out.println ("Estoy en: " + l1208);
    result = "L1208";
} else if ((l1209 > pasillo) && (l1209 > l1207) && (l1209 > l1208) && (l1209 > l1210) && (l1209 > l1211) && (l1209 > l1212) && 
        (l1209 > l1213) && (l1209 > ninguno)) {                               
    System.out.println ("Estoy en: " + l1209);
    result = "L1209";
} else if ((l1210 > pasillo) && (l1210 > l1207) && (l1210 > l1208) && (l1210 > l1209) && (l1210 > l1211) && 
        (l1210 > l1212) && (l1210 > l1213) && (l1210 > ninguno)) {
    System.out.println ("Estoy en: " + l1210);
    result = "L1210";
} else if ((l1211 > pasillo) && (l1211 > l1207) && (l1211 > l1208) && (l1211 > l1209) && (l1211 > l1210) && (l1211 > l1212) && 
        (l1211 > l1213) && (l1211 > ninguno)) {
    System.out.println ("Estoy en: " + l1211);
    result = "L1211";
} else if ((l1212 > pasillo) && (l1212 > l1207) && (l1212 > l1208) && (l1212 > l1209) && (l1212 > l1210) && (l1212 > l1211) &&
        (l1212 > l1213) && (l1212 > ninguno)) {
    System.out.println ("Estoy en: " + l1212);
    result = "L1212";
} else if ((l1213 > pasillo) && (l1213 > l1207) && (l1213 > l1208) && (l1213 > l1209) && (l1213 > l1210) && (l1213 > l1211) &&
        (l1213 > l1212) && (l1213 > ninguno)) {
    System.out.println ("Estoy en: " + l1213);
    result = "L1213";
} else if ((ninguno > pasillo) && (ninguno > l1207) && (ninguno > l1208) && (ninguno > l1209) && (ninguno > l1210) && 
        (ninguno > l1211) && (ninguno > l1212) && (ninguno > l1213)){                                           
    System.out.println ("Estoy en: " + ninguno);
    result = "Ninguno";
}
        
        } 
        
      return result; 
    } 
}
