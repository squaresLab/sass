public class Plan1571768625015 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}


} else {
ShutdownServer("A");
}

}

}
}
