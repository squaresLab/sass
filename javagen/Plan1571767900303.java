public class Plan1571767900303 extends Plan { 
public static void main(String[] args) { 
ShutdownServer("B");
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

StartServer("C");
StartServer("C");

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}



}


}
}
