public class Plan1571775083848 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("B");
StartServer("C");
DecreaseTraffic("A");


} else {

DecreaseTraffic("A");

}


}

}
}
