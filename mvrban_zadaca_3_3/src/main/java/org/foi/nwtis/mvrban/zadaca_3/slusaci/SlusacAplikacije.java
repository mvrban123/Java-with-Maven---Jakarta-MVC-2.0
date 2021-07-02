package org.foi.nwtis.mvrban.zadaca_3.slusaci;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.mvrban.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.mvrban.vjezba_03.konfiguracije.NeispravnaKonfiguracija;

@WebListener
public class SlusacAplikacije implements ServletContextListener {

    /**
     * Funckija koja uništava spremljeni kontekst konfiguracije kod zatvaranja aplikacije
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("Postavke");
    }

    /**
     * Funkcija koja dohvaća podatke konfiguracije iz datoteke prilikom pokretanja aplikacije
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();
        
        String putanjaKonfDatoteka = servletContext.getRealPath("WEB-INF")
                +File.separator+servletContext.getInitParameter("konfiguracija");
        
        PostavkeBazaPodataka pbp = new PostavkeBazaPodataka(putanjaKonfDatoteka);
        try {
            pbp.ucitajKonfiguraciju();
            servletContext.setAttribute("Postavke", pbp);
        } catch (NeispravnaKonfiguracija ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }   
}
