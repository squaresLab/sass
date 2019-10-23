public class Plan1571774059928 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

DecreaseTraffic("B");
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

StartServer("C");




}
}
