public class Plan1571768388197 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("C");

} else {
StartServer("B");
DecreaseTraffic("A");

}

}

}
}
