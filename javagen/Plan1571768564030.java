public class Plan1571768564030 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseTraffic("A");
}

} else {

}


}

}
}
