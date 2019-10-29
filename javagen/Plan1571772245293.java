public class Plan1571772245293 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}


StartServer("B");

} else {
if ( IncreaseTraffic("C") ) {
if ( DecreaseTraffic("C") ) {
for (int i = 0; i < 3 ; i++) {
ShutdownServer("A");
}

} else {
IncreaseDimmer("C");
}

} else {
DecreaseTraffic("A");
}

StartServer("A");

}

StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}



}

}
}
