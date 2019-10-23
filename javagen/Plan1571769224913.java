public class Plan1571769224913 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
IncreaseDimmer("C");
StartServer("B");

}

} else {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("B");
}

}

}

}
}
