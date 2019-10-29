public class Plan1571773306986 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}


StartServer("A");



}

}
}
