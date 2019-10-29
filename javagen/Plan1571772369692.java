public class Plan1571772369692 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
if ( DecreaseDimmer("A") ) {
IncreaseDimmer("C");
} else {
IncreaseTraffic("A");
}

} else {
for (int i = 0; i < 6 ; i++) {
IncreaseTraffic("B");
}

}

DecreaseTraffic("A");

DecreaseTraffic("A");

}
}
