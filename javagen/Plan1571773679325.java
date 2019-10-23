public class Plan1571773679325 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
if ( StartServer("A") ) {
StartServer("B");
StartServer("C");

} else {
StartServer("B");
}

} else {
StartServer("A");
}

StartServer("C");

} else {
StartServer("B");
}

}

}
}
