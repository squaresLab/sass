public class Plan1571775304193 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

StartServer("A");

}

} else {
if ( StartServer("C") ) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
ShutdownServer("C");
}

} else {
StartServer("B");
}



} else {
ShutdownServer("B");
}

}

} else {
DecreaseTraffic("C");
}

}
}
