public class Plan1525889199914 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

DecreaseTraffic("A");
StartServer("A");



}

}
}
