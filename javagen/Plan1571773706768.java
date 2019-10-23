public class Plan1571773706768 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("C");
StartServer("B");

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

} else {
StartServer("B");
}

if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
ShutdownServer("B");
}


}

}
}
