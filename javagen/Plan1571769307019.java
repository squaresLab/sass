public class Plan1571769307019 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( IncreaseTraffic("C") ) {
IncreaseDimmer("B");
DecreaseTraffic("A");

} else {
if ( ShutdownServer("A") ) {
DecreaseTraffic("A");
} else {
if ( IncreaseTraffic("C") ) {
ShutdownServer("C");
} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

}

}

}

for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}


}

}
}
