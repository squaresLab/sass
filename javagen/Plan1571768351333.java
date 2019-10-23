public class Plan1571768351333 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("B");
}


DecreaseDimmer("A");

} else {
ShutdownServer("A");
}

}

}
}
