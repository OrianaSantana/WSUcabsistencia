/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oriana
 */
@XmlRootElement(name="historico_asistencia")
public class HistoricoAsistencia extends ENTIDAD{
    public int hist_asis_id;
    public String hist_asis_fecha;
    public String hist_asis_tipo;
    public boolean hist_asis_estado;
    public Justificacion hist_asis_justificacion;
    public Horario hist_asis_horario;  
    public String hist_asis_observacion;

    public HistoricoAsistencia(int hist_asis_id, String hist_asis_fecha, String hist_asis_tipo, boolean hist_asis_estado, Justificacion hist_asis_justificacion, Horario hist_asis_horario, String hist_asis_observacion) {
        this.hist_asis_id = hist_asis_id;
        this.hist_asis_fecha = hist_asis_fecha;
        this.hist_asis_tipo = hist_asis_tipo;
        this.hist_asis_estado = hist_asis_estado;
        this.hist_asis_justificacion = hist_asis_justificacion;
        this.hist_asis_horario = hist_asis_horario;
        this.hist_asis_observacion = hist_asis_observacion;
    }

   public HistoricoAsistencia() {
        this.hist_asis_id = 0;
        this.hist_asis_fecha = null;
        this.hist_asis_tipo = null;
        this.hist_asis_estado = false;
        this.hist_asis_justificacion = null;
        this.hist_asis_horario = null;
        this.hist_asis_observacion = null;
    }


    @XmlElement(required=true)
    public int getHist_asis_id() {
        return hist_asis_id;
    }

    public void setHist_asis_id(int hist_asis_id) {
        this.hist_asis_id = hist_asis_id;
    }

    public String getHist_asis_fecha() {
        return hist_asis_fecha;
    }

    public void setHist_asis_fecha(String hist_asis_fecha) {
        this.hist_asis_fecha = hist_asis_fecha;
    }

   

    public String getHist_asis_tipo() {
        return hist_asis_tipo;
    }

    public void setHist_asis_tipo(String hist_asis_tipo) {
        this.hist_asis_tipo = hist_asis_tipo;
    }

    public boolean isHist_asis_estado() {
        return hist_asis_estado;
    }

    public void setHist_asis_estado(boolean hist_asis_estado) {
        this.hist_asis_estado = hist_asis_estado;
    }

    public String getHist_asis_observacion() {
        return hist_asis_observacion;
    }

    public void setHist_asis_observacion(String hist_asis_observacion) {
        this.hist_asis_observacion = hist_asis_observacion;
    }
    


     @XmlElement(required=true)
    public Justificacion getHist_asis_justificacion() {
        return hist_asis_justificacion;
    }

    public void setHist_asis_justificacion(Justificacion hist_asis_justificacion) {
        this.hist_asis_justificacion = hist_asis_justificacion;
    }

     @XmlElement(required=true)
    public Horario getHist_asis_horario() {
        return hist_asis_horario;
    }

    public void setHist_asis_horario(Horario hist_asis_horario) {
        this.hist_asis_horario = hist_asis_horario;
    }
    
    @Override
    public String toString() {
        return "Historico Asistencia{" + '"'+"hist_asis_id"+'"'+":"+ hist_asis_id + ","+'"'+"hist_asis_fecha"+'"'+":"+'"'+ hist_asis_fecha +'"'+ ","+'"'+"hist_asis_tipo"+'"'+":"+'"'+hist_asis_tipo +'"'+ ","+'"'+"hist_asis_estado" +'"'+":"+'"'+ hist_asis_estado +'"'+ ","+'"'+"hist_asis_justificacion" +'"'+":"+ '"'+hist_asis_justificacion +'"'+ ","+'"'+"hist_asis_horario" +'"'+":"+ '"'+hist_asis_horario + '"'+'}';
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
        hash = 83 * hash + this.hist_asis_id;
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
        final HistoricoAsistencia other = (HistoricoAsistencia) obj;
        if (this.hist_asis_id != other.hist_asis_id) {
            return false;
        }
        if (this.hist_asis_tipo != other.hist_asis_tipo) {
            return false;
        }
        if (this.hist_asis_estado != other.hist_asis_estado) {
            return false;
        }
        if (!Objects.equals(this.hist_asis_fecha, other.hist_asis_fecha)) {
            return false;
        }
        if (!Objects.equals(this.hist_asis_justificacion, other.hist_asis_justificacion)) {
            return false;
        }
        if (!Objects.equals(this.hist_asis_horario, other.hist_asis_horario)) {
            return false;
        }
        return true;
    }
    
    
}
