public class Plan1571769812298 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}

StartServer("C");
if ( DecreaseTraffic("A") ) {
DecreaseDimmer("C");
} else {
DecreaseDimmer("A");
}



}

StartServer("C");
if ( IncreaseTraffic("B") ) {
DecreaseDimmer("C");
} else {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


}



}
}
