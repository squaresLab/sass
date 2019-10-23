public class Plan1571768424360 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}


}

}
}
