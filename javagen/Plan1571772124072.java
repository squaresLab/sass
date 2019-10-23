public class Plan1571772124072 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("A") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("C");
}

StartServer("C");
StartServer("B");


} else {
DecreaseTraffic("A");
}

}

}
}
