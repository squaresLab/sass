public class Plan1571775435638 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}

} else {
StartServer("B");
}

StartServer("B");


}

}
}
