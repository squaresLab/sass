public class Plan1525889218474 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("B");
if ( StartServer("B") ) {
DecreaseDimmer("B");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
StartServer("C");

StartServer("B");

}

}

}

for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("A");
}

}




}
}
