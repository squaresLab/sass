public class Plan1571775090153 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("C");
}

} else {
StartServer("A");
}


}

}
}
