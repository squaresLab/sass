public class Plan1571774765092 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {

}

} else {
if ( DecreaseDimmer("A") ) {
ShutdownServer("C");
} else {
DecreaseDimmer("B");
}

}


}

StartServer("A");


}
}
