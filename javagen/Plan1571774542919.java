public class Plan1571774542919 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");
IncreaseTraffic("C");


}

if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseTraffic("C");
}


}
}
