public class Plan1571772628884 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
if ( StartServer("B") ) {
StartServer("C");
StartServer("A");

} else {
StartServer("B");
}


StartServer("C");

} else {
ShutdownServer("A");
}

}

}
}
