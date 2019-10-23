public class Plan1571769516072 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");
StartServer("C");


} else {
ShutdownServer("B");
}

StartServer("A");
StartServer("B");


}


}
}
