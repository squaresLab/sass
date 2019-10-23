public class Plan1571769800013 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

} else {
StartServer("B");
}

} else {
IncreaseDimmer("A");
}


}

}
}
