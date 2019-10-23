public class Plan1571771496274 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");
StartServer("A");

StartServer("B");


} else {
ShutdownServer("A");
}

}

StartServer("C");
StartServer("B");



}
}
