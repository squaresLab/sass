public class Plan1571772671858 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

StartServer("A");

StartServer("B");

}

} else {
StartServer("B");
}

}
}
