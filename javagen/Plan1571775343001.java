public class Plan1571775343001 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
DecreaseTraffic("A");
DecreaseDimmer("B");

}

StartServer("B");

}

}
}
