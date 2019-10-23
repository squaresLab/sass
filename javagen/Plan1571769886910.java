public class Plan1571769886910 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("A");
}

} else {
StartServer("B");
}

} else {
StartServer("B");
}


}

}

}
}
