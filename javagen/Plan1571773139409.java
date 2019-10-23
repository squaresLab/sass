public class Plan1571773139409 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");
StartServer("A");
StartServer("C");
StartServer("B");




} else {
StartServer("A");
}

}

}
}
