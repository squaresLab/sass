public class Plan1571775670125 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( IncreaseTraffic("B") ) {
ShutdownServer("A");
} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}

} else {
DecreaseTraffic("A");
}

StartServer("C");

}

}
}
