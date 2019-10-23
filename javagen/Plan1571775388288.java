public class Plan1571775388288 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("A");

} else {
StartServer("A");
StartServer("C");

}


}

}
}
