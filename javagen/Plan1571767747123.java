public class Plan1571767747123 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("C");

StartServer("B");

} else {
DecreaseTraffic("A");
}


}

}
}
