public class Plan1571774598899 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}

StartServer("C");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}

StartServer("A");


StartServer("B");



StartServer("A");

}

}
}
