public class Plan1571775051507 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("B");
}

StartServer("B");

} else {
ShutdownServer("A");
}

}

}
}
