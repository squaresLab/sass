public class Plan1571774991001 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
StartServer("B");
} else {
IncreaseDimmer("B");
}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
StartServer("B");

} else {
StartServer("C");
DecreaseDimmer("A");

}

} else {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

}

}


}
}
