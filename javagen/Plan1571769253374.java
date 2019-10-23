public class Plan1571769253374 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
DecreaseTraffic("A");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("C");

StartServer("A");

}

} else {
StartServer("B");
}

StartServer("C");



}
}
