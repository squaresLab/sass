public class Plan1571775170850 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

StartServer("B");
StartServer("A");


} else {
StartServer("B");
}

}

}


StartServer("B");

StartServer("C");

}
}
