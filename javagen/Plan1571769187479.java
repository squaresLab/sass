public class Plan1571769187479 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}

StartServer("B");

}


}
}
