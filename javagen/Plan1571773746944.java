public class Plan1571773746944 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
StartServer("C");

} else {
StartServer("B");
StartServer("A");

}

} else {
StartServer("B");
}



}

}
}
