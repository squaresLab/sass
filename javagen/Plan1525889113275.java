public class Plan1525889113275 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
IncreaseDimmer("A");
DecreaseTraffic("A");

}

} else {
StartServer("A");
}

}

}
}
