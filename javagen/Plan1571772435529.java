public class Plan1571772435529 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {

StartServer("B");
StartServer("C");
StartServer("A");



}

} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");
StartServer("C");
StartServer("A");



}

} else {
DecreaseTraffic("C");
}

}

}

}
}
