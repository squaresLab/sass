public class Plan1571768021674 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("A");

StartServer("C");

StartServer("B");

} else {
IncreaseTraffic("B");
}

}

}
}
