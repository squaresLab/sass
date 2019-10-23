public class Plan1571768356097 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
StartServer("B");
StartServer("C");

DecreaseDimmer("A");

}


}

}
}
