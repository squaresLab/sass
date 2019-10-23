public class Plan1571773410800 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

} else {
StartServer("C");
}

StartServer("C");
StartServer("B");


DecreaseTraffic("A");

}

}
}
