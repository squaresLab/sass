public class Plan1571771960237 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("C");
StartServer("B");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
DecreaseTraffic("A");

}




}

}
}
