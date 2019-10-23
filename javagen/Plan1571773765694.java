public class Plan1571773765694 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}


}

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

}
}
