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
@XmlRootElement(name="notificacion")
public class Notificacion extends ENTIDAD{
    public int not_id;
    public String not_mensaje;

    public Notificacion() {
        this.not_id = 0;
        this.not_mensaje = null;
    }
    
    public Notificacion(int not_id, String not_mensaje) {
        this.not_id = not_id;
        this.not_mensaje = not_mensaje;
    }

       @XmlElement(required=true)
    public int getNot_id() {
        return not_id;
    }

    public void setNot_id(int not_id) {
        this.not_id = not_id;
    }

       @XmlElement(required=true)
    public String getNot_mensaje() {
        return not_mensaje;
    }

    public void setNot_mensaje(String not_mensaje) {
        this.not_mensaje = not_mensaje;
    }

    
    @Override
    public String toString() {
        return "Notificacion{" + '"'+"not_id"+'"'+":"+ not_id + ","+'"'+"not_mensaje"+'"'+":"+'"'+ not_mensaje +'"'+'}';
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
        int hash = 5;
        hash = 41 * hash + this.not_id;
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
        final Notificacion other = (Notificacion) obj;
        if (this.not_id != other.not_id) {
            return false;
        }
        if (!Objects.equals(this.not_mensaje, other.not_mensaje)) {
            return false;
        }
        return true;
    }
    
}
