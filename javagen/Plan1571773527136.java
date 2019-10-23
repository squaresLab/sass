public class Plan1571773527136 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

StartServer("C");

}

} else {
DecreaseTraffic("C");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
