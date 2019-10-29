public class Plan1571775580052 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
IncreaseTraffic("C");
}

StartServer("B");

StartServer("A");

}

}
}
