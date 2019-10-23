public class Plan1571771509123 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("A");

} else {
ShutdownServer("A");
}

}

DecreaseDimmer("A");


if ( StartServer("B") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}


}
}
