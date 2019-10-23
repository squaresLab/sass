public class Plan1571768339729 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("B");
}

}

}

}

}
}
