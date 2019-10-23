public class Plan1571774088079 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

} else {
StartServer("B");
}

StartServer("B");

}

}
}
