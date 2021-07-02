package org.foi.nwtis.mvrban.zadaca_3.wsep;

import jakarta.annotation.PostConstruct;
import jakarta.jms.Queue;
import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.OnOpen;
import jakarta.websocket.WebSocketContainer;
import jakarta.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ClientEndpoint()
public class OglasnikAerodroma {
    
    private Session session;
    
    /**
     * Funckija koja služi za spajanje na ServerEndpoint iz zadace_3_3 
     */
    @PostConstruct
    public void spajanje() {
        String uri = "ws://localhost:8380/mvrban_zadaca_3_3/ws";
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(this, new URI(uri));
        } catch (Exception ex) {
            
        }
    }

    /**
     * Funckija koja služi za slanje poruka prema ServerEndpoint u zadaca_3_3
     *
     * @param poruka
     */
    public void posaljiPoruku(String poruka){
        try {
            System.out.println("Oglasnik aerodroma:"+poruka);
            session.getBasicRemote().sendText(poruka);
        } catch (Exception ex) {
        }
    }
}
