public class Plan1571768436511 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}


}

}
}
