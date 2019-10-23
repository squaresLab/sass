public class Plan1571775076998 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
StartServer("C");

}


}

}
}
