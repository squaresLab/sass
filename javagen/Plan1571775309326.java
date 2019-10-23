public class Plan1571775309326 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

} else {
DecreaseTraffic("A");
}

StartServer("B");

}

}
}
