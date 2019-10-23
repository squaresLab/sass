public class Plan1571772310073 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}


DecreaseDimmer("A");

} else {
StartServer("C");
}

}

}
}
