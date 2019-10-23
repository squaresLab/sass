public class Plan1571767729647 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

StartServer("A");

}

} else {
for (int i = 0; i < 3 ; i++) {
IncreaseDimmer("B");
}

}

}

} else {
ShutdownServer("B");
}

}
}
