public class Plan1571767944004 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}



StartServer("C");

StartServer("C");

}

}
}
