public class Plan1571773376178 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
StartServer("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}




DecreaseTraffic("A");

}

}
}
