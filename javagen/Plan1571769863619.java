public class Plan1571769863619 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");

if ( StartServer("C") ) {
StartServer("B");
StartServer("A");

} else {
DecreaseDimmer("C");


}


} else {
StartServer("A");
}

}

}
}
