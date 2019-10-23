public class Plan1571772859236 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}


} else {
StartServer("A");
}


}

}
}
