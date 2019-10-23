public class Plan1571769214737 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}


}

} else {
DecreaseTraffic("A");
}

}

StartServer("A");

StartServer("A");

}
}
