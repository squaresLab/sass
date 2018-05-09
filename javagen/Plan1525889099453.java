public class Plan1525889099453 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( DecreaseDimmer("C") ) {
ShutdownServer("B");
} else {
StartServer("A");
}

if ( StartServer("B") ) {
StartServer("C");
StartServer("A");

} else {
ShutdownServer("A");
}



}

StartServer("B");


}
}
