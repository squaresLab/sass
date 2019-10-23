public class Plan1571767836964 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

}

}

}
}
