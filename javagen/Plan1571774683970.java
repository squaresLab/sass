public class Plan1571774683970 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
if ( StartServer("B") ) {
if ( StartServer("C") ) {
StartServer("A");
DecreaseTraffic("A");

} else {
StartServer("A");
StartServer("B");

}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

}

}

}
}
