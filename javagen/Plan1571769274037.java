public class Plan1571769274037 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("A");
StartServer("C");

} else {
StartServer("C");
StartServer("B");

}

} else {
StartServer("B");
}

StartServer("A");
StartServer("C");


}

}
}
