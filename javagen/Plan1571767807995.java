public class Plan1571767807995 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("B");
StartServer("A");


} else {
StartServer("A");
}


}

}
}
