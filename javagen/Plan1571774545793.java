public class Plan1571774545793 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("C");
}

}


}

}
}
