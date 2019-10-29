public class Plan1571772666240 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
IncreaseTraffic("B");
}

StartServer("B");
StartServer("A");


}

}
}
