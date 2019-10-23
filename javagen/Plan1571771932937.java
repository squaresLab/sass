public class Plan1571771932937 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}

} else {
StartServer("B");
}

StartServer("C");
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}

} else {
DecreaseTraffic("A");
}

DecreaseTraffic("A");

}



}
}
