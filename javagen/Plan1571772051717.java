public class Plan1571772051717 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

StartServer("A");

} else {
StartServer("A");
}

StartServer("C");

StartServer("A");

}

}
}
