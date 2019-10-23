public class Plan1571772270155 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}



}
}
