public class Plan1571767875562 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
StartServer("B");

} else {
DecreaseDimmer("C");
}

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}


}

}
}
