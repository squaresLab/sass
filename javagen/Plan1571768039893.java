public class Plan1571768039893 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}

StartServer("A");

} else {
IncreaseTraffic("B");
}

if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}


}

} else {
for (int i = 0; i < 2 ; i++) {
IncreaseDimmer("B");
}

}

}
}
