public class Plan1571770534953 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

} else {
DecreaseTraffic("A");
}


}

}
}
