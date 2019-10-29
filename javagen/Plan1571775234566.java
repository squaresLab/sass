public class Plan1571775234566 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("A");
} else {
if ( IncreaseTraffic("B") ) {
ShutdownServer("A");
} else {
StartServer("A");
}

}

}

StartServer("B");

}

}
}
