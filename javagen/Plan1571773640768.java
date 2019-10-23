public class Plan1571773640768 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
StartServer("A");
StartServer("B");
DecreaseTraffic("A");



} else {
StartServer("B");
}

}

}
}
