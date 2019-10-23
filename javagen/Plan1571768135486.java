public class Plan1571768135486 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("A");
}

StartServer("B");

StartServer("A");



}

if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}


}
}
