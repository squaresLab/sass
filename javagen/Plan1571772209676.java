public class Plan1571772209676 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("C");

} else {

}

}


}
}
