public class Plan1571768846655 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

} else {
StartServer("B");
}

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}


}

}
}
