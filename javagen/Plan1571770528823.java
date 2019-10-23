public class Plan1571770528823 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("C");

StartServer("A");
StartServer("C");


StartServer("A");
StartServer("B");


} else {
ShutdownServer("A");
}

}

}
}
