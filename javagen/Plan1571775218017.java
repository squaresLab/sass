public class Plan1571775218017 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}

} else {
StartServer("C");
}

}

}
}
