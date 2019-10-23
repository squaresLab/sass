public class Plan1571768690786 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}

StartServer("A");


if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseDimmer("A");
}

} else {
StartServer("A");
StartServer("C");

}



StartServer("C");

}

}
}
