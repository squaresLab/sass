public class Plan1571767761354 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
if ( DecreaseDimmer("C") ) {

} else {
StartServer("A");
}

} else {
StartServer("C");


}


StartServer("B");

StartServer("B");

}

}
}
