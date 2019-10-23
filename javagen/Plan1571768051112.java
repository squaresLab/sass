public class Plan1571768051112 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("C");

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("A");


} else {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

}

}

StartServer("A");

}
}
