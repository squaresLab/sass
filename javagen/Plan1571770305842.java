public class Plan1571770305842 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
StartServer("C");

DecreaseTraffic("A");

}


DecreaseTraffic("A");

}

}
}
