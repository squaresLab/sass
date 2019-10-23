public class Plan1571772256146 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("A");

}

DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



} else {
DecreaseTraffic("C");
}

}
}
