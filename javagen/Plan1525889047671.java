public class Plan1525889047671 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
if ( StartServer("C") ) {
DecreaseDimmer("C");
} else {
DecreaseDimmer("C");
}

} else {
DecreaseTraffic("C");
}

} else {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

StartServer("B");

}

}


}
}
