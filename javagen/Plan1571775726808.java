public class Plan1571775726808 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
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
