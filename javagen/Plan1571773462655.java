public class Plan1571773462655 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");

if ( DecreaseTraffic("A") ) {

} else {
DecreaseTraffic("A");
}


}

}
}
