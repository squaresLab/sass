public class Plan1571775688180 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}

DecreaseTraffic("A");

StartServer("C");

} else {
IncreaseDimmer("C");
}

}

}
}
