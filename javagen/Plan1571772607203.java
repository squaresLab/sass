public class Plan1571772607203 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}


}

StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("A");

}



}
}
