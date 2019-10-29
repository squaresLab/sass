public class Plan1571775671585 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("A");
IncreaseTraffic("B");

} else {
StartServer("A");
DecreaseTraffic("A");

}

}

}

}
}
