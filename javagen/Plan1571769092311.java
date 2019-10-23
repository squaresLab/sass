public class Plan1571769092311 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("A");

} else {
StartServer("C");
}

DecreaseTraffic("A");
StartServer("B");


}

}
}
