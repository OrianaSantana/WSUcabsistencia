/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ENTIDAD;
import Dominio.FABRICAENTIDAD;

/**
 *
 * @author el_je_000
 */
public class DAORol extends DAOGeneral implements IDAORol{

   private ENTIDAD _rol = FABRICAENTIDAD.obtenerRol(0, null, null, null);
    
}
