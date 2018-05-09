public class Plan1525889276395 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
if ( DecreaseTraffic("B") ) {
DecreaseTraffic("C");
} else {
IncreaseDimmer("A");
}

}

}

for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {

}

}


}
}
