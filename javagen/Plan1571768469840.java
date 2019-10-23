public class Plan1571768469840 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

DecreaseTraffic("A");

} else {
StartServer("A");
}

}

}
}
