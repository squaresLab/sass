public class Plan1571774997787 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

StartServer("C");

}

StartServer("A");

}
}
