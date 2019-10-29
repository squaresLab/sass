public class Plan1571773902606 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
if ( StartServer("B") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
IncreaseTraffic("C");
}

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
