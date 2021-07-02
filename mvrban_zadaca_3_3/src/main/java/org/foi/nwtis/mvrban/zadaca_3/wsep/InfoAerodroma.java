package org.foi.nwtis.mvrban.zadaca_3.wsep;

import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ServerEndpoint("/ws")
public class InfoAerodroma {
    
    static Queue<Session> redSjednica = new ConcurrentLinkedQueue<>();
    /**
     * Funckija koja šalje poruku svim aktivnim sjednicama
     * @param message
     * @param session 
     */
    @OnMessage
    public void stiglaPoruka(String message, Session session){
        System.out.println("Stigla WS poruka:" + message);
        try {
            for (Session sess : session.getOpenSessions()) {
                if (sess.isOpen()) {
                    sess.getBasicRemote().sendText(message);
                }
            }
        } catch (Exception ex) {
        }
    }
    
    /**
     * Funckija koja upravlja greškama kod ServerEndPoint
     * @param s tipa klase Session
     * @param throwable tipa klase Throwable
     */
    @OnError
    public void onError(Session s, Throwable throwable){
        redSjednica.remove(s);
    }
    
}
