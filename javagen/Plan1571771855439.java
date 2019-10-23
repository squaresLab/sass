public class Plan1571771855439 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}


DecreaseTraffic("A");

}

} else {
if ( StartServer("C") ) {
StartServer("A");
} else {
IncreaseDimmer("A");
}

}

}
}
