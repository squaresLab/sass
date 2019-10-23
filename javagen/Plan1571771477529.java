public class Plan1571771477529 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");
StartServer("A");


} else {
DecreaseTraffic("C");
}

StartServer("B");
StartServer("A");
StartServer("B");
StartServer("A");
StartServer("A");





}

} else {
StartServer("B");
StartServer("A");

}

}
}
