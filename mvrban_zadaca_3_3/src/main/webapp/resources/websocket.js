var wsocket;
//Funkcija koja služi za spajanje na websocket
function connect() {
    wsocket = new WebSocket("ws://" + document.location.host+"/mvrban_zadaca_3_3/" + "ws");
    wsocket.onmessage = onMessage;
}
//Funckija koja definira što se dešava kada stigne poruka
function onMessage(evt) {
    var arraypv = evt.data.split(": ");
    document.getElementById("broj").innerHTML = arraypv[1];
    //document.getElementById("volume").innerHTML = arraypv[1];
}
window.addEventListener("load", connect, false);

