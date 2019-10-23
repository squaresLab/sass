public class Plan1571774526299 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
StartServer("C");
StartServer("B");

StartServer("A");

} else {
StartServer("C");
}

StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {

}

StartServer("C");

}



}
}
