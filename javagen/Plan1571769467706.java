public class Plan1571769467706 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
IncreaseDimmer("A");
}

if ( StartServer("B") ) {
StartServer("C");
DecreaseTraffic("A");

} else {

}



}



}
}
