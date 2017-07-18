/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oriana
 */

@XmlRootElement(name="justificacion")
public class Justificacion extends ENTIDAD{
    public int jus_id;
    public String jus_nombre;  

    public Justificacion() {
        this.jus_id = 0;
        this.jus_nombre = null;
    }
    
    public Justificacion(int jus_id, String jus_nombre) {
        this.jus_id = jus_id;
        this.jus_nombre = jus_nombre;       
    }

       @XmlElement(required=true)
    public int getJus_id() {
        return jus_id;
    }

    public void setJus_id(int jus_id) {
        this.jus_id = jus_id;
    }

       @XmlElement(required=true)
    public String getJus_nombre() {
        return jus_nombre;
    }

    public void setJus_nombre(String jus_nombre) {
        this.jus_nombre = jus_nombre;
    }

    
    @Override
    public String toString() {
        return "Justificacion{" + '"'+"jus_id"+'"'+":"+ jus_id + ","+'"'+"jus_nombre"+'"'+":"+'"'+ jus_nombre +'"'+'}';
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.jus_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Justificacion other = (Justificacion) obj;
        if (this.jus_id != other.jus_id) {
            return false;
        }
        if (!Objects.equals(this.jus_nombre, other.jus_nombre)) {
            return false;
        }
        return true;
    }
    
    
}

