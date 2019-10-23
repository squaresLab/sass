public class Plan1571772352256 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
StartServer("B");
StartServer("A");

StartServer("A");


for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseDimmer("A");
}

StartServer("B");
StartServer("C");


}


}
}
