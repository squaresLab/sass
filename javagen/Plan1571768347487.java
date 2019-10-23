public class Plan1571768347487 extends Plan { 
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
StartServer("C");
}


}

}
}
