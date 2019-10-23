public class Plan1571770177732 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 6 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

StartServer("A");

}


StartServer("B");

}
}
