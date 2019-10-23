public class Plan1571775414696 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("A");

} else {
StartServer("B");
}

if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}



}

}
}
