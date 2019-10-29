public class Plan1571770340303 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");
if ( StartServer("A") ) {
StartServer("B");
DecreaseTraffic("A");

StartServer("C");

} else {
StartServer("A");
IncreaseTraffic("B");

}



}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( IncreaseTraffic("A") ) {
IncreaseDimmer("B");
} else {
StartServer("C");
}


}

}

}
}
