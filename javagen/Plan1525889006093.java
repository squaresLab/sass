public class Plan1525889006093 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("B");

} else {
for (int i = 0; i < 2 ; i++) {
DecreaseDimmer("A");
}

}

}

}
}
