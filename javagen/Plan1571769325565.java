public class Plan1571769325565 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("C");
}

}

StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseDimmer("C");
}



StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseDimmer("A");
}



}
}
