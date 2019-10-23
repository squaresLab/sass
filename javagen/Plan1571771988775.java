public class Plan1571771988775 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("C");
}

StartServer("B");
DecreaseTraffic("A");
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
if ( StartServer("B") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

}


for (int i = 0; i < 4 ; i++) {
StartServer("A");
}





}
}
