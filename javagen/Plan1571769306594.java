public class Plan1571769306594 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("A");

StartServer("B");
StartServer("C");


} else {
StartServer("B");
}

}

}
}
