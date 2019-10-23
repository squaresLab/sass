public class Plan1571767689911 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
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

}

} else {
StartServer("A");
}

}
}
