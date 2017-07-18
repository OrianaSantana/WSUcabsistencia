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
@XmlRootElement(name="rol")
public class Rol extends ENTIDAD{
    public int rol_id;
    public String rol_descripcion;
    public String rol_accion;
    public List<Profesor> rol_profesor;

    public Rol() {
        this.rol_id = 0;
        this.rol_descripcion = null;
        this.rol_accion = null;
        this.rol_profesor = null;
    }

    public Rol(int rol_id, String rol_descripcion, String rol_accion, List<Profesor> rol_profesor) {
        this.rol_id = rol_id;
        this.rol_descripcion = rol_descripcion;
        this.rol_accion = rol_accion;
        this.rol_profesor = rol_profesor;
    }

       @XmlElement(required=true)
    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
    
   @XmlElement(required=true)
    public String getRol_descripcion() {
        return rol_descripcion;
    }

    public void setRol_descripcion(String rol_descripcion) {
        this.rol_descripcion = rol_descripcion;
    }

       @XmlElement(required=true)
    public String getRol_accion() {
        return rol_accion;
    }

    public void setRol_accion(String rol_accion) {
        this.rol_accion = rol_accion;
    }

       @XmlElement(required=true)
    public List<Profesor> getRol_profesor() {
        return rol_profesor;
    }

    public void setRol_profesor(List<Profesor> rol_profesor) {
        this.rol_profesor = rol_profesor;
    }
    
    @Override
    public String toString() {
        return "Rol{" + '"'+"rol_id"+'"'+":"+ rol_id + ","+'"'+"rol_descripcion"+'"'+":"+'"'+ rol_descripcion +'"'+ ","+'"'+"rol_accion"+'"'+":"+'"'+rol_accion +'"'+ ","+'"'+"rol_profesor" +'"'+":"+'"'+ rol_profesor + '"'+'}';
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
        hash = 83 * hash + this.rol_id;
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
        final Rol other = (Rol) obj;
        if (this.rol_id != other.rol_id) {
            return false;
        }
        if (!Objects.equals(this.rol_descripcion, other.rol_descripcion)) {
            return false;
        }
        if (!Objects.equals(this.rol_accion, other.rol_accion)) {
            return false;
        }
        if (!Objects.equals(this.rol_profesor, other.rol_profesor)) {
            return false;
        }
        return true;
    }
    
}
