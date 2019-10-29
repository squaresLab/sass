public class Plan1571772767298 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
StartServer("A");
}

StartServer("B");

DecreaseTraffic("C");
for (int i = 0; i < 5 ; i++) {
if ( StartServer("A") ) {
IncreaseTraffic("B");
} else {
StartServer("A");
}

}

StartServer("B");

StartServer("B");



}
}
