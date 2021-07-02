package org.foi.nwtis.mvrban.zadaca_3.ejb.sb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.foi.nwtis.mvrban.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.OWMKlijent;
import org.foi.nwtis.rest.podaci.MeteoPodaci;


@Stateless
public class Meteorolog {
    
    /**
     * Funckija koja dohvaća meteo podatke za određeni aerodrom sa servisa Open Weather Map
     * @param aerodrom klase Aerodrom
     * @param pbp klase PostavkeBazaPodataka
     * @return MeteoPodaci
     */
    public MeteoPodaci dajMeteoPodatke(Aerodrom aerodrom, PostavkeBazaPodataka pbp){      
        OWMKlijent oWMKlijent = new OWMKlijent(pbp.dajPostavku("OpenWeatherMap.apikey"));
        MeteoPodaci mp = oWMKlijent.getRealTimeWeather(aerodrom.getLokacija().getLatitude(), aerodrom.getLokacija().getLongitude());
        return mp;
    }
}
