public class Plan1525889189156 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("B");
}

DecreaseTraffic("A");
StartServer("A");




}

}
}
