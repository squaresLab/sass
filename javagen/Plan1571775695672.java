public class Plan1571775695672 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

StartServer("B");


}

} else {
StartServer("C");
}

}
}
