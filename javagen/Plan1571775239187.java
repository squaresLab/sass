public class Plan1571775239187 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}


StartServer("B");

} else {
ShutdownServer("A");
}

}

}
}
