public class Plan1571768598073 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

DecreaseTraffic("A");

DecreaseDimmer("A");


}

} else {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

}

}
}
