public class Plan1571773456654 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
DecreaseTraffic("A");

} else {
StartServer("B");
}


}

StartServer("A");

}
}
