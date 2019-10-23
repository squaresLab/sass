public class Plan1571770424530 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

}

if ( StartServer("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
StartServer("B");

}


StartServer("A");
DecreaseTraffic("A");


}

}
}
