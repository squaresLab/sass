public class Plan1571769751126 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

StartServer("B");

}

}

}
}
