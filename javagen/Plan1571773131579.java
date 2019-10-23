public class Plan1571773131579 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");
StartServer("A");


}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

}

StartServer("B");
StartServer("A");



StartServer("C");

}
}
