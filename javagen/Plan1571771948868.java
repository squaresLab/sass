public class Plan1571771948868 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
StartServer("B");
StartServer("A");


} else {
DecreaseDimmer("B");
}

StartServer("B");
DecreaseTraffic("A");


}

}
}
