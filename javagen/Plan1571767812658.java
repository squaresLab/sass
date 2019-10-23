public class Plan1571767812658 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("B");
}


}


for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}


}
}
