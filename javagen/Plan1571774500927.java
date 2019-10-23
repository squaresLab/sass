public class Plan1571774500927 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("C");
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}



StartServer("C");

if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
StartServer("B");

}



DecreaseTraffic("A");

}

}
}
