public class Plan1571772639686 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("A");

if ( StartServer("C") ) {
StartServer("A");
StartServer("B");
StartServer("A");
StartServer("A");



} else {
StartServer("C");
StartServer("B");

StartServer("A");

StartServer("A");

}



StartServer("C");

}

}
}
