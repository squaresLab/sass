public class Plan1571773875531 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
StartServer("C");
StartServer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");

}




if ( IncreaseTraffic("B") ) {
ShutdownServer("C");
} else {
DecreaseTraffic("A");
}


StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}


}
}
