public class Plan1525889277436 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {
IncreaseDimmer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}

}



}

} else {
if ( IncreaseDimmer("A") ) {
DecreaseDimmer("A");
} else {
StartServer("A");
}

DecreaseTraffic("C");

DecreaseTraffic("A");

}

}
}
