public class Plan1571767724757 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

if ( StartServer("C") ) {
StartServer("C");
StartServer("C");

} else {
StartServer("A");
}


}
}
