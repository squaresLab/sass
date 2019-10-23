public class Plan1571772653514 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("C");
}


} else {
StartServer("C");
}

} else {
StartServer("C");
}

}

}
}
