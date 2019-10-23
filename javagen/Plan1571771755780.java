public class Plan1571771755780 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

DecreaseDimmer("A");

} else {
StartServer("A");
}

}

}
}
