public class Plan1571775573396 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("B");
}

if ( StartServer("C") ) {
DecreaseDimmer("A");
} else {
StartServer("A");
}

for (int i = 0; i < 5 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("C");
}

}




}
}
