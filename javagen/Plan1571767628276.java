public class Plan1571767628276 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

} else {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


}

} else {
if ( StartServer("C") ) {
if ( IncreaseDimmer("A") ) {
StartServer("C");
StartServer("C");



DecreaseDimmer("A");

} else {
ShutdownServer("A");
}

StartServer("C");

} else {
DecreaseTraffic("A");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


}
}
