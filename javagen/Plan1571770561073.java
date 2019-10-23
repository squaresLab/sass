public class Plan1571770561073 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("C");
}

StartServer("B");
DecreaseTraffic("A");


}


}
}
