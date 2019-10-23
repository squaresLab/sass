public class Plan1571775541483 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}


if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}


}

}
}
