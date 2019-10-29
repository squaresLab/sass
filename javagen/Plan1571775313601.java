public class Plan1571775313601 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
StartServer("C");
} else {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
DecreaseTraffic("A");

} else {
StartServer("B");
IncreaseTraffic("B");

}

}

StartServer("A");

StartServer("C");
DecreaseTraffic("A");



}
}
