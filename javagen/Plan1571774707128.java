public class Plan1571774707128 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("C");

StartServer("B");

} else {
StartServer("A");
}


}

}
}
