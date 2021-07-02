/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mvrban.vjezba_03.konfiguracije;

/**
 *
 * @author NWTiS_2
 */
public class NeispravnaKonfiguracija extends Exception{

    /**
     * Konstruktor klase NeispravnaKonfiguracija
     */
    public NeispravnaKonfiguracija() {
    }
     
    /**
     * Konstruktor klase NeispravnaKonfiguracija
     * @param msg 
     */
    public NeispravnaKonfiguracija(String msg) {
        super(msg);
    }
    
}
