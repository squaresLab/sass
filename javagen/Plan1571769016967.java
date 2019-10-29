public class Plan1571769016967 extends Plan { 
public static void main(String[] args) { 
IncreaseTraffic("B");
DecreaseTraffic("A");
DecreaseTraffic("A");

DecreaseTraffic("A");


for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("A");
StartServer("B");


} else {
StartServer("C");
}

}


}
}
