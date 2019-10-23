public class Plan1571769452246 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
DecreaseTraffic("C");
}

} else {
DecreaseTraffic("A");
}


}

}
}
