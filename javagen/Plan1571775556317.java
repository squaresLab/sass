public class Plan1571775556317 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
DecreaseTraffic("A");
}

DecreaseTraffic("A");

}

}
}
