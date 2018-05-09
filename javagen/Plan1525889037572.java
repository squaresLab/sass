public class Plan1525889037572 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

} else {
DecreaseTraffic("B");
}


for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("B");
}


}


}
}
