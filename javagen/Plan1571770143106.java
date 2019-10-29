public class Plan1571770143106 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("B");

IncreaseTraffic("C");

} else {
StartServer("A");
}

}

StartServer("B");

StartServer("B");
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}




}
}
