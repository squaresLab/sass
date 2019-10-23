public class Plan1571768958542 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("B");
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
StartServer("A");


}

}



}
}
