public class Plan1571771865819 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

StartServer("B");

} else {
StartServer("C");
}

StartServer("B");

}

}
}
