public class Plan1571775231478 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


}

StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


} else {
StartServer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


}


}



}
}
