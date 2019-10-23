public class Plan1571770390050 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
StartServer("A");
} else {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("C");

}

}

}

}
}
