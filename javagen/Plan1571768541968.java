public class Plan1571768541968 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

if ( StartServer("B") ) {
ShutdownServer("A");
} else {
StartServer("B");
}


} else {
ShutdownServer("A");
}

}

}
}
