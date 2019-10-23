public class Plan1571774319785 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("B");
}

} else {
StartServer("B");
}

StartServer("C");
StartServer("A");


}

}
}
