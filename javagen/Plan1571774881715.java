public class Plan1571774881715 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}


}

}
}
