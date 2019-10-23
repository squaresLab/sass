public class Plan1571775291788 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

if ( StartServer("C") ) {
if ( DecreaseDimmer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

} else {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

}


}
}
