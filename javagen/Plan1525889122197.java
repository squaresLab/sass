public class Plan1525889122197 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
if ( DecreaseTraffic("B") ) {
StartServer("C");
} else {
ShutdownServer("A");
}

StartServer("C");



for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseTraffic("C");
}


}



for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}
}
