public class Plan1571773186731 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}


StartServer("C");

}

}
}
