/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mvrban.konfiguracije.bazaPodataka;

import java.util.Properties;
import org.foi.nwtis.mvrban.vjezba_03.konfiguracije.Konfiguracija;

/**
 *
 * @author NWTiS_2
 */
public interface KonfiguracijaBP extends Konfiguracija {
    
    String getAdminDatabase();
    String getAdminPassword();
    String getAdminUsername();
    String getDriverDatabase();
    String getDriverDatabase(String urlBazePodataka);
    Properties getDriversDatabase();
    String getServerDatabase();
    String getUserDatabase();
    String getUserPassword();
    String getUserUsername();    
}
