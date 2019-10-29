public class Plan1571774566489 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("C");
}

} else {
IncreaseTraffic("C");
}


}

}
}
