public class Plan1571774802840 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}


}

StartServer("C");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}




}
}
