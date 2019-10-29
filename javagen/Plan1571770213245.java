public class Plan1571770213245 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("B");
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



} else {
IncreaseTraffic("A");
}

} else {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


}

}

}
}
