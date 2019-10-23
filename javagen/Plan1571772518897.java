public class Plan1571772518897 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("B");
}




}

StartServer("A");
StartServer("C");

StartServer("B");


}
}
