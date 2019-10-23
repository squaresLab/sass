public class Plan1571769905603 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("A") ) {

} else {
DecreaseTraffic("A");
}

} else {
StartServer("C");
}



}

}
}
