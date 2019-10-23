public class Plan1571772005056 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

StartServer("A");

}

} else {
if ( StartServer("B") ) {
DecreaseTraffic("A");
StartServer("B");

} else {
IncreaseDimmer("B");
StartServer("C");

}

}

}

StartServer("A");

}
}
