public class Plan1571769723077 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("B");

} else {
DecreaseDimmer("B");
StartServer("A");

}

}

}
}
