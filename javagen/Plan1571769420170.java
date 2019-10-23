public class Plan1571769420170 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("B");

} else {
StartServer("A");
}

StartServer("B");

}

} else {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("B");

} else {
ShutdownServer("B");
}

IncreaseDimmer("C");

}

}

}
}
