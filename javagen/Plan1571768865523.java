public class Plan1571768865523 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("A") ) {
StartServer("C");
StartServer("B");

} else {
IncreaseTraffic("C");
}

StartServer("C");
DecreaseTraffic("A");



}

}
}
