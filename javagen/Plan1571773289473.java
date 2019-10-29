public class Plan1571773289473 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("A");
DecreaseTraffic("A");


DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}


StartServer("A");


if ( StartServer("C") ) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
DecreaseDimmer("B");
DecreaseTraffic("A");


} else {
IncreaseTraffic("B");
}


} else {
IncreaseTraffic("B");
}


}
}
