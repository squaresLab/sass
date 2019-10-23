public class Plan1571772204810 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("C");
StartServer("B");


}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("A");

}

if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("C");



}
}
