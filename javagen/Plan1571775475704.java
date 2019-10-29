public class Plan1571775475704 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
IncreaseTraffic("C");
}

StartServer("A");
StartServer("C");


}

}
}
