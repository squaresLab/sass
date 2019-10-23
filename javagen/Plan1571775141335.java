public class Plan1571775141335 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


StartServer("A");

}


}
}
