public class Plan1571768395876 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("C");
StartServer("B");

}

} else {
StartServer("B");
}

StartServer("C");

}

}
}
