public class Plan1571769623403 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}



StartServer("C");
StartServer("B");


}

}
}
