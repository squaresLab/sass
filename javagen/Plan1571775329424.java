public class Plan1571775329424 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}



}



}
}
