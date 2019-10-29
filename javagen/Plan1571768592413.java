public class Plan1571768592413 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}

IncreaseTraffic("B");
DecreaseDimmer("C");
if ( IncreaseTraffic("B") ) {
DecreaseTraffic("A");
} else {
ShutdownServer("A");
}




IncreaseTraffic("B");
DecreaseDimmer("A");


} else {
DecreaseTraffic("A");
}


}
}
