public class Plan1571774887915 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("A");

} else {
StartServer("A");
}

} else {
if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("B");
}

IncreaseTraffic("B");

}

}

}
}
