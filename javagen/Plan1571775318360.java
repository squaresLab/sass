public class Plan1571775318360 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

} else {
StartServer("C");
}

}

}
}
