public class Plan1571769737786 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}


} else {
StartServer("A");
}

}


}
}
