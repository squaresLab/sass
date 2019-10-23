public class Plan1571774852493 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

if ( DecreaseTraffic("B") ) {
DecreaseDimmer("C");
} else {
if ( StartServer("B") ) {
if ( StartServer("A") ) {
for (int i = 0; i < 4 ; i++) {

}

} else {
IncreaseDimmer("B");
}

DecreaseDimmer("A");

} else {
IncreaseDimmer("C");
}

DecreaseTraffic("A");

}


}
}
