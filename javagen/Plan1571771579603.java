public class Plan1571771579603 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}


StartServer("B");

} else {
StartServer("A");
}

}

StartServer("C");

}
}
