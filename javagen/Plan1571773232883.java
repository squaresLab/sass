public class Plan1571773232883 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}

}

StartServer("B");

for (int i = 0; i < 2 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}

}

StartServer("B");

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("A");
StartServer("A");



StartServer("A");
StartServer("A");



}
}
