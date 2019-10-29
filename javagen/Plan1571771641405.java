public class Plan1571771641405 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

if ( IncreaseTraffic("C") ) {
StartServer("B");
} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");

}

}


}
}
