public class Plan1571773483928 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

}

StartServer("C");

}

}
}
