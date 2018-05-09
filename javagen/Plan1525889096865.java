public class Plan1525889096865 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
ShutdownServer("B");
} else {
StartServer("A");
}

if ( StartServer("B") ) {
DecreaseTraffic("C");
} else {
ShutdownServer("A");
}


}

}
}
