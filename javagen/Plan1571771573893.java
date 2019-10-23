public class Plan1571771573893 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}

} else {
StartServer("A");
}

StartServer("B");

}

} else {
StartServer("B");
}

}
}
