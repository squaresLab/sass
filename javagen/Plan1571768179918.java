public class Plan1571768179918 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("C");
}


} else {
StartServer("A");
}

}

}
}
