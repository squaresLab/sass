public class Plan1571774006936 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
IncreaseDimmer("B");
StartServer("B");

}


StartServer("B");
DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}




}

}
}
