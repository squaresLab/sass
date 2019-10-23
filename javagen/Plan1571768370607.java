public class Plan1571768370607 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}



}


}
}
