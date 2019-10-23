public class Plan1571770433347 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

StartServer("C");

}

}
}
