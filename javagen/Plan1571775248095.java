public class Plan1571775248095 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseTraffic("C");
}

}

if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseTraffic("C");
}

} else {
DecreaseTraffic("C");
}


}

}
}
