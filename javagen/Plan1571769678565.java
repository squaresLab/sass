public class Plan1571769678565 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("A");

} else {
StartServer("B");
}

if ( DecreaseDimmer("C") ) {
DecreaseTraffic("B");
} else {
DecreaseTraffic("A");
}

StartServer("B");


}

StartServer("A");

}
}
