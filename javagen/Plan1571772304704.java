public class Plan1571772304704 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

if ( StartServer("C") ) {
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
