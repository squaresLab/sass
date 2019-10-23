public class Plan1571769208338 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

if ( StartServer("A") ) {
StartServer("B");
StartServer("C");

} else {
StartServer("A");
}



}

}
}
