public class Plan1571773041362 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
ShutdownServer("B");
}

if ( StartServer("B") ) {
StartServer("C");
} else {

}


DecreaseTraffic("A");

}

StartServer("A");

}
}
