public class Plan1571775368693 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

} else {
DecreaseTraffic("C");
}


if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 6 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
StartServer("C");

}


}
}
