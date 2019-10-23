public class Plan1571772924190 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}

StartServer("C");


DecreaseTraffic("A");

}


}
}
