public class Plan1571769050149 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


} else {
DecreaseTraffic("A");
}


}

}
}
