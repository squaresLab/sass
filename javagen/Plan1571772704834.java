public class Plan1571772704834 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}


}

StartServer("B");

} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

StartServer("A");
StartServer("A");


}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

}

}

} else {
DecreaseTraffic("C");
}

}
}
