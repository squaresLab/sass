public class Plan1571773118862 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
StartServer("B");

DecreaseTraffic("A");

} else {
StartServer("A");
}

DecreaseTraffic("A");
StartServer("B");


}

}
}
