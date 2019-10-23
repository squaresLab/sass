public class Plan1571773425201 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("A");
}

} else {
StartServer("C");
}

}

}
}
