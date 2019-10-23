public class Plan1571772363404 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("C");
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}



} else {
DecreaseDimmer("C");
}


}

}
}
