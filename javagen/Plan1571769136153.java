public class Plan1571769136153 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

StartServer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
