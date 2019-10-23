public class Plan1571769645547 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");
DecreaseTraffic("A");


} else {
StartServer("C");
}

}

}
}
