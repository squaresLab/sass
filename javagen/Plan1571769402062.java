public class Plan1571769402062 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}


} else {
StartServer("C");
StartServer("B");

StartServer("C");

}

StartServer("A");

}

}
}
