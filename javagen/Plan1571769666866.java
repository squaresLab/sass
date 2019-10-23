public class Plan1571769666866 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("B");
StartServer("C");

DecreaseTraffic("A");
StartServer("B");


} else {
DecreaseTraffic("A");
}

}

StartServer("C");

StartServer("B");
StartServer("C");

DecreaseTraffic("A");
DecreaseDimmer("A");



}
}
