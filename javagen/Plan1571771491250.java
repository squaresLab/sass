public class Plan1571771491250 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
ShutdownServer("B");
}

} else {
StartServer("B");
}

}

StartServer("C");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}



}
}
