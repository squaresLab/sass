public class Plan1571768501908 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("C");
StartServer("B");

}

} else {
StartServer("B");
}


}

}
}
