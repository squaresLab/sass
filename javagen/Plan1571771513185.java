public class Plan1571771513185 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
StartServer("C");

}

} else {
if ( StartServer("B") ) {
StartServer("C");
} else {

}

}

} else {
StartServer("B");
}

}

}
}
