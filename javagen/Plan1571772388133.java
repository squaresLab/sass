public class Plan1571772388133 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
DecreaseTraffic("A");
StartServer("B");

StartServer("B");

if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("B");
}



}



}
}
