public class Plan1571774469672 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
StartServer("A");
StartServer("A");

}


}

}
}
