public class Plan1571774228825 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


} else {
StartServer("A");
}

StartServer("B");

}


StartServer("A");

}
}
