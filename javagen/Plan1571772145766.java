public class Plan1571772145766 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
DecreaseTraffic("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}



StartServer("B");

}

}
}
