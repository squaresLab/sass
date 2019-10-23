public class Plan1571772118867 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("B");
StartServer("A");


} else {
StartServer("A");
}

StartServer("B");

}

}
}
