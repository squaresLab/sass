public class Plan1571771547714 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}



DecreaseTraffic("A");

}

StartServer("A");

}
}
