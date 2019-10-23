public class Plan1571771595925 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}


}

}
}
