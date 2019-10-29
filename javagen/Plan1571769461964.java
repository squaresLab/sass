public class Plan1571769461964 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("A");
}

}


StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("A");
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("C");

StartServer("A");




}
}
