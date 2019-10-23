public class Plan1571772170609 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("A");
if ( StartServer("B") ) {
StartServer("B");
StartServer("C");

DecreaseDimmer("A");

} else {
StartServer("B");
DecreaseDimmer("A");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");

}



for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
