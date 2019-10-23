public class Plan1571772999856 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}

if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("C");



}

}
}
