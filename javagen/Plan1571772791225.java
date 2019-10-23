public class Plan1571772791225 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("C");
}



}

}
}
