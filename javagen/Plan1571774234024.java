public class Plan1571774234024 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
IncreaseTraffic("A");
}

} else {
DecreaseDimmer("C");
DecreaseDimmer("A");

}

} else {
DecreaseDimmer("C");
DecreaseTraffic("A");

}

}

}
}
