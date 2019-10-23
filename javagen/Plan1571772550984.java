public class Plan1571772550984 extends Plan { 
public static void main(String[] args) { 
DecreaseDimmer("B");
StartServer("B");
StartServer("A");

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {

}

}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}




}
}
