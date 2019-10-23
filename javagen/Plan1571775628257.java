public class Plan1571775628257 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("B");
StartServer("A");
StartServer("C");


} else {
StartServer("C");
}

} else {
DecreaseDimmer("C");
}

}

}
}
