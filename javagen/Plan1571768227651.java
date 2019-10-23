public class Plan1571768227651 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}


StartServer("B");

}

}
}
