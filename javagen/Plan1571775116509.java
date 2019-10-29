public class Plan1571775116509 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}



IncreaseTraffic("C");

StartServer("A");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}



StartServer("B");

}

}
}
