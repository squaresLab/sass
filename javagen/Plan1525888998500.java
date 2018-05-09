public class Plan1525888998500 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("B") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("C");
StartServer("B");

}

DecreaseTraffic("A");

} else {
for (int i = 0; i < 2 ; i++) {
ShutdownServer("B");
}

}

StartServer("A");

}
}
