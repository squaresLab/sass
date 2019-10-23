public class Plan1571767668730 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}

} else {
DecreaseDimmer("A");
StartServer("B");

}

StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}



StartServer("B");


}

}
}
