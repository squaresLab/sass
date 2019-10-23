public class Plan1571768193109 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("C");
}

} else {
StartServer("B");
}

DecreaseTraffic("A");
StartServer("A");
StartServer("C");



}

}
}
