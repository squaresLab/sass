public class Plan1571768035432 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}


DecreaseDimmer("A");

} else {
ShutdownServer("C");
}

}

}
}
