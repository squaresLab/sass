public class Plan1571768117473 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("A");
}

} else {
StartServer("B");
}

StartServer("C");

}

}
}
