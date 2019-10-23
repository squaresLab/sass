public class Plan1571771984497 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("A");
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("A");
}

}




DecreaseTraffic("A");

}

}
}
