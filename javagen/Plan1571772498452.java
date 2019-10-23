public class Plan1571772498452 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
StartServer("B");
StartServer("C");
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("A");
}





}

}
}
