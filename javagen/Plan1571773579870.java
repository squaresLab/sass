public class Plan1571773579870 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("B");
}

StartServer("A");

}

}
}
