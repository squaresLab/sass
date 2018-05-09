public class Plan1525889112404 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
DecreaseDimmer("C");

} else {
DecreaseDimmer("A");
}

if ( DecreaseDimmer("C") ) {

} else {
StartServer("A");
}



}

StartServer("B");


}
}
