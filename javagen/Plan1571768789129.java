public class Plan1571768789129 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

StartServer("B");

}

}
}
