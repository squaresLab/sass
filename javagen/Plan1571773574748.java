public class Plan1571773574748 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {

}


} else {
if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {

}

}

if ( StartServer("A") ) {
StartServer("A");
} else {

}

StartServer("C");



}

StartServer("A");

}
}
