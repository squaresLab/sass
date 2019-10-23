public class Plan1571771809162 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("B");
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
}

StartServer("C");



}

}
}
