public class Plan1571775745674 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

StartServer("C");
StartServer("A");


}

}
}
