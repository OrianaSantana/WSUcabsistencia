/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oriana
 */
@XmlRootElement(name="huella")
public class Huella extends ENTIDAD{
    public int hue_id;
    public double hue_x;
    public double hue_y;
    public double hue_z;
    public Salon salon;
    public String hue_salon;

    public Huella() {
        this.hue_id = 0;
        this.hue_x = 0.0;
        this.hue_y = 0.0;
        this.hue_z = 0.0;
        this.hue_salon = null;
    }

    public Huella(int hue_id, double hue_x, double hue_y, double hue_z, Salon salon, String hue_salon) {
        this.hue_id = hue_id;
        this.hue_x = hue_x;
        this.hue_y = hue_y;
        this.hue_z = hue_z;
        this.salon = salon;
        this.hue_salon = hue_salon;
    }
    
    
       @XmlElement(required=true)
    public int getHue_id() {
        return hue_id;
    }

    public void setHue_id(int hue_id) {
        this.hue_id = hue_id;
    }

       @XmlElement(required=true)
    public double getHue_x() {
        return hue_x;
    }

    public void setHue_x(double hue_x) {
        this.hue_x = hue_x;
    }

       @XmlElement(required=true)
    public double getHue_y() {
        return hue_y;
    }

    public void setHue_y(double hue_y) {
        this.hue_y = hue_y;
    }

       @XmlElement(required=true)
    public double getHue_z() {
        return hue_z;
    }

    public void setHue_z(double hue_z) {
        this.hue_z = hue_z;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public String getHue_salon() {
        return hue_salon;
    }

    public void setHue_salon(String hue_salon) {
        this.hue_salon = hue_salon;
    }

      
    
    @Override
    public String toString() {
        return "Huella{" + '"'+"hue_id"+'"'+":"+ hue_id + ","+'"'+"hue_x"+'"'+":"+'"'+ hue_x +'"'+ ","+'"'+"hue_y"+'"'+":"+'"'+hue_y +'"'+ ","+'"'+"hue_z" +'"'+":"+'"'+ hue_z +'"'+ ","+'"'+"hue_salon" +'"'+":"+ '"'+hue_salon + '"'+'}';
    }

    public String toJson(){
    
        Gson gson = new Gson();
 
	// convert java object to JSON format,
	// and returned as JSON formatted string
	String json = gson.toJson(this);
 
	try {
		//write converted json data to a file named "file.json"
		FileWriter writer = new FileWriter("c:\\file.json");
		writer.write(json);
		writer.close();
                return json;
 
	} catch (IOException e) {
		e.printStackTrace();
	}
        
       return null; 
        
    }

   


    
}
