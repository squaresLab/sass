public class Plan1571773819946 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("C");

} else {
StartServer("B");
}

} else {
StartServer("B");
}

}

}
}
