public class Plan1571773331445 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("A");
DecreaseDimmer("A");
StartServer("B");



StartServer("C");

} else {
StartServer("B");
}

}


}
}
