public class Plan1571772589992 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

DecreaseTraffic("A");



}

}
}
