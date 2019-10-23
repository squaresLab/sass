public class Plan1571768630768 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("C");
}

StartServer("B");

} else {
StartServer("C");
}

StartServer("B");

}

}
}
