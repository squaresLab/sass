public class Plan1571773036844 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("B");
}

} else {
StartServer("B");
}

} else {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("C");
}

} else {
StartServer("B");
}

}


}

StartServer("B");

}
}
