
package org.foi.nwtis.mvrban.zadaca_3.zrna;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.mvrban.zadaca_3.ejb.sb.OsobniAerodromi;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.mvrban.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.mvrban.zadaca_3.ejb.sb.Meteorolog;
import org.foi.nwtis.rest.podaci.MeteoPodaci;

@Named(value = "pregledAerodroma")
@RequestScoped
public class PregledAerodroma {

    @EJB
    OsobniAerodromi osobniAerodromi;
    
    @Inject
    Meteorolog meteorolog;
    
    @Inject
    ServletContext servletContext;
    
    @Getter
    @Setter
    private String brojAerodroma;
    
    @Getter
    @Setter
    private String odabraniAerdorom;
    
    @Getter
    @Setter
    private String nazivAerdoroma;
    
    @Getter
    @Setter
    private String temperatura;
    
    @Getter
    @Setter
    private String vlaga;

    @Getter
    @Setter
    private String tlak;
    
    private List<Aerodrom> evidentiraniAerodromi = new ArrayList<>();
    
    /**
     * Konstruktor klase PregledAerodroma
     */
    public PregledAerodroma() {
        
    }

    /**
     * Funckija koja dohvaća sve evidentirane aerodrome iz aplikacijske evidencije i vraća ih u obliku liste aerodroma
     * @return List Aerodrom 
     */
    public List<Aerodrom> getEvidentiraniAerodromi() {
        getBrojAerodroma();
        this.evidentiraniAerodromi = osobniAerodromi.dajEvidentiraneAerodrome();
        return evidentiraniAerodromi;
    }
    
    /**
     * Funckija koja dohvaća broj aerodroma koji su spremljeni u aplikacijskoj evidenciji
     * @return String
     */
    public String getBrojAerodroma(){
        this.brojAerodroma = Integer.toString(osobniAerodromi.brojAerodroma());
        
        return brojAerodroma;
    }
    
    /**
     * Funkcija koja dohvaća meteo podatke na temelju lokacijskih podataka za određeni aerodrom
     * @return String
     */
    public String dajMeteoPodatke(){
        try {
            if (odabraniAerdorom.length() < 1) {
                return null;
            }
        } catch(Exception ex){
            return null;
        }       
        PostavkeBazaPodataka pbp = (PostavkeBazaPodataka) servletContext.getAttribute("Postavke");
        Aerodrom aerodrom = osobniAerodromi.dohvatiAerodrom(odabraniAerdorom);
        MeteoPodaci mp = meteorolog.dajMeteoPodatke(aerodrom, pbp);
        nazivAerdoroma = osobniAerodromi.dohvatiNazivAerodroma(odabraniAerdorom);
        postaviTemperaturu(mp);
        postaviVlagu(mp);
        postaviTlak(mp);
        return nazivAerdoroma;
    }
    
    /**
     * Funckija koja postavlja parametar temperature dobiven iz meteo podataka
     * @param mp
     * @return String
     */
    public String postaviTemperaturu(MeteoPodaci mp){
        temperatura = "Temp: " + Math.round(mp.getTemperatureValue()) + " " + mp.getTemperatureUnit();
        return temperatura;
    }
    
    /**
     * Funckija koja postavlja parametar vlage dobiven iz meteo podataka
     * @param mp
     * @return String
     */
    public String postaviVlagu(MeteoPodaci mp){
        vlaga = "Vlaga: " + Math.round(mp.getHumidityValue()) + " " + mp.getHumidityUnit();
        return vlaga;
    }
    
    /**
     * Funckija koja postavlja parametar tlaka dobiven iz meteo podataka
     * @param mp
     * @return String
     */
    public String postaviTlak(MeteoPodaci mp){
        tlak = "Tlak: " + Math.round(mp.getPressureValue()) + " " + mp.getPressureUnit();
        return tlak;
    }
    
    /**
     * Funckija koja služi za osvježavanje prikaza evidentiranih aerodroma i broja aerodroma spremljenih u aplikacijskoj evidenciji
     * @return String
     */
    public String osvjeziAerodrome(){
        getEvidentiraniAerodromi();
        getBrojAerodroma();
        return "";
    }   
}
