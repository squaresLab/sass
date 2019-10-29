public class Plan1571771764135 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}

StartServer("B");
StartServer("C");
StartServer("A");



}

StartServer("B");
StartServer("A");


}
}
