public class Plan1571775191378 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("A");
}

}

StartServer("B");



}

StartServer("C");


}
}
