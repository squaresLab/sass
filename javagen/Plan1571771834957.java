public class Plan1571771834957 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

if ( DecreaseDimmer("B") ) {
IncreaseDimmer("A");
} else {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("C");
}

}


}

}
}
