public class Plan1571773896327 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("B");
}

StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
StartServer("C");

StartServer("B");

}


if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}



}

}
}
