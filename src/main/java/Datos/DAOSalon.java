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
public class DAOSalon extends DAOGeneral implements IDAOSalon {
    
    private ENTIDAD _salon = FABRICAENTIDAD.obtenerSalon(0, null, null, null);
}
