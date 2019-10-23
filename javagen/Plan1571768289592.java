public class Plan1571768289592 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

StartServer("C");


} else {
StartServer("A");
}

}

}
}
