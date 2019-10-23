public class Plan1571768694811 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("B");

}


}

} else {
ShutdownServer("B");
}

} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
} else {

}

StartServer("A");

}

} else {
DecreaseTraffic("C");
}

}

}
}
