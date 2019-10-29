public class Plan1571773665768 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
if ( IncreaseTraffic("B") ) {
DecreaseDimmer("A");
} else {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");

}

}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

StartServer("A");


}
}
