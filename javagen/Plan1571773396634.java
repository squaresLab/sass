public class Plan1571773396634 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

} else {
if ( StartServer("C") ) {
StartServer("C");
} else {
StartServer("B");
}

}

}

}
}
