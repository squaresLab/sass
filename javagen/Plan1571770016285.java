public class Plan1571770016285 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");
StartServer("B");

if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}



IncreaseTraffic("A");

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}



IncreaseTraffic("A");

}

}
}
