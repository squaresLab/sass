public class Plan1571770001522 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("A");

} else {
StartServer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}

}

}
}
