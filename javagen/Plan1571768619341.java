public class Plan1571768619341 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
StartServer("B");

} else {
StartServer("B");
StartServer("C");

}

DecreaseTraffic("A");
StartServer("B");


}

}
}
