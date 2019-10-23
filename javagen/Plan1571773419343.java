public class Plan1571773419343 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

StartServer("B");
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

}

StartServer("B");
StartServer("B");




}
}
