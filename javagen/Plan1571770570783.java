public class Plan1571770570783 extends Plan { 
public static void main(String[] args) { 
IncreaseTraffic("A");
StartServer("B");
StartServer("B");
DecreaseTraffic("A");


for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("B");
DecreaseTraffic("A");


} else {
StartServer("C");
}

StartServer("A");

}



}
}
