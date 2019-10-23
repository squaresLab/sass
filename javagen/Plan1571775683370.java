public class Plan1571775683370 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}

if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("B");



}


}
}
