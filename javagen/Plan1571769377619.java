public class Plan1571769377619 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

}

StartServer("B");

StartServer("B");



}
}
