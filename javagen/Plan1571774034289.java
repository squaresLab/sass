public class Plan1571774034289 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {

}



StartServer("B");

}

}
}
