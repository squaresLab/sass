public class Plan1571769778069 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

DecreaseDimmer("A");

} else {
StartServer("B");
StartServer("A");

}

}

}
}
