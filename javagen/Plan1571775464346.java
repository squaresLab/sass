public class Plan1571775464346 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
StartServer("C");
IncreaseTraffic("B");

}

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}

}
}
