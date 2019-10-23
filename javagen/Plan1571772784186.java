public class Plan1571772784186 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

if ( StartServer("C") ) {
StartServer("B");
} else {
IncreaseDimmer("A");
}


} else {
DecreaseTraffic("A");
}

}

}
}
