public class Plan1571768605282 extends Plan { 
public static void main(String[] args) { 
DecreaseDimmer("C");
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}

if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}


}


}
}
