public class Plan1571768235682 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
StartServer("A");

if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("C");
}


StartServer("B");

StartServer("C");

StartServer("C");

} else {

StartServer("C");

StartServer("A");

StartServer("B");

StartServer("C");

}

}

}
}
