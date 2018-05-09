public class Plan1525889112700 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseTraffic("C");
}


}


}
}
