public class Plan1571773869643 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

StartServer("B");

}

}
}
