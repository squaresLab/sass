public class Plan1571775700234 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( IncreaseTraffic("C") ) {
ShutdownServer("C");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");

StartServer("C");

} else {
StartServer("A");
}

StartServer("B");

}

}

}
}
