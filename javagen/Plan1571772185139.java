public class Plan1571772185139 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}



StartServer("C");

}

}
}
