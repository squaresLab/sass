public class Plan1571775521353 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");
StartServer("A");

StartServer("B");


StartServer("C");

} else {
StartServer("A");
}

}

}
}
