public class Plan1571774874006 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
DecreaseTraffic("A");
DecreaseDimmer("B");

}

StartServer("A");

}

StartServer("C");

StartServer("C");
DecreaseTraffic("A");


if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

StartServer("A");


}
}
