public class Plan1571775196539 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}



StartServer("C");

}

}
}
