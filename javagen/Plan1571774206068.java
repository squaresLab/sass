public class Plan1571774206068 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("C");
DecreaseTraffic("A");


} else {
StartServer("B");
StartServer("C");
DecreaseTraffic("A");


}

} else {
StartServer("B");
}

StartServer("B");

}

}
}
