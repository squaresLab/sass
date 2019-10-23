public class Plan1571771767609 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("C");
}

if ( ShutdownServer("C") ) {
DecreaseDimmer("B");
} else {
if ( DecreaseTraffic("C") ) {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("C");
}

if ( ShutdownServer("C") ) {
DecreaseDimmer("B");
} else {
DecreaseTraffic("C");
}


} else {
ShutdownServer("C");
}

}


}

}
}
