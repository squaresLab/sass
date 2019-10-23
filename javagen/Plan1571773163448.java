public class Plan1571773163448 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}

} else {
StartServer("B");
}

if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}



}

}
}
