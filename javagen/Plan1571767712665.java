public class Plan1571767712665 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("C");
}

} else {
DecreaseTraffic("A");
}


}

}
}
