public class Plan1571772082612 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("C");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("B");
DecreaseTraffic("A");

}

StartServer("B");



}


}
}
