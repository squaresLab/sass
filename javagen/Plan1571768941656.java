public class Plan1571768941656 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}

StartServer("B");

}

} else {
DecreaseTraffic("C");
}

}
}
