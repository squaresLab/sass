public class Plan1571768017987 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
}

StartServer("B");
DecreaseTraffic("A");



}

StartServer("C");

}
}
