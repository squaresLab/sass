public class Plan1571774513604 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}

}

if ( IncreaseTraffic("B") ) {
DecreaseDimmer("A");
} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");

}

}


}
}
