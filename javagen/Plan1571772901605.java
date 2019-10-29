public class Plan1571772901605 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("C");
StartServer("B");

StartServer("A");

}

} else {
StartServer("C");
}

IncreaseTraffic("B");

StartServer("B");

DecreaseTraffic("A");

}
}
