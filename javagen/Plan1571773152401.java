public class Plan1571773152401 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
StartServer("C");

} else {
StartServer("B");
}

StartServer("A");
StartServer("B");
StartServer("A");
StartServer("C");




DecreaseTraffic("A");
StartServer("C");


}


}
}
