public class Plan1571775645510 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

IncreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}


}


if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}


StartServer("A");



}
}
