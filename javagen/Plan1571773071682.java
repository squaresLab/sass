public class Plan1571773071682 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

StartServer("B");

}

}
}
